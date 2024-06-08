package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.service.AccountService; // Assuming AccountService exists
import com.cashub.cashhubbackend.cashub.domain.category.Category;
import com.cashub.cashhubbackend.cashub.service.CategoryService; // Assuming CategoryService exists
import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import com.cashub.cashhubbackend.cashub.repository.TransactionRepository;
import com.cashub.cashhubbackend.cashub.domain.account.exception.AccountNotFoundException;
import com.cashub.cashhubbackend.cashub.domain.transaction.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    public Transaction createTransaction(Transaction transaction, Long accountId, Long categoryId) {
        // 1. Find account by accountId
        Account account = accountService.getAccount(accountId);

        // 2. Find category by categoryId (optional)
        Category category = null;
        if (categoryId != null) {
            category = categoryService.getCategory(categoryId);
        }

        // 3. Set associations (account and category)
        transaction.setAccount(account.getUser());
        transaction.setCategory(category);

        // 4. Validate transaction amount and account balance (for expense transactions)
        if (transaction.getType().equals("expense") && transaction.getValue().compareTo(account.getBalance()) > 0) {
            throw new InsufficientFundsException("Insufficient funds for transaction");
        }


        // 5. Update account balance (consider implementing in a separate service for better separation of concerns)
        account.setBalance(account.getBalance() - transaction.getValue());
        accountService.updateAccount(account);

        // 6. Set transaction date
        transaction.setDate(new Date());

        // 7. Save transaction to database
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) {
        // 1. Find transaction by id
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        // 2. Check if transaction exists
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found"); // Consider creating a specific exception for transaction not found
        }

        // 3. Return transaction
        return transactionOptional.get();
    }

    public List<Transaction> getAllTransactions() {
        // 1. Find all transactions
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        // 1. Find transactions by account id
        return transactionRepository.findByAccountId(accountId);
    }

    // Additional methods specific to transaction management can be added here
    // For example, update transaction, delete transaction functionalities etc.
}
