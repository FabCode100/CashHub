package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.repository.TransactionRepository;
import com.cashub.cashhubbackend.cashub.domain.category.Category;
import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import com.cashub.cashhubbackend.cashub.domain.transaction.exception.InsufficientFundsException;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    public Transaction createTransaction(Transaction transaction, Long accountId, Long categoryId) {
        Account account = accountService.getAccount(accountId);

        Category category = null;
        if (categoryId != null) {
            category = categoryService.getCategory(categoryId);
        }

        transaction.setAccount(account);
        transaction.setCategory(category);

        if ("expense".equals(transaction.getType()) && transaction.getAmount() > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds for transaction");
        }

        account.setBalance(account.getBalance() - transaction.getAmount());
        accountService.updateAccount(account);

        transaction.setDate(new Date());

        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found");
        }
        return transactionOptional.get();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // Additional methods specific to transaction management can be added here
}
