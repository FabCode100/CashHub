package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.domain.category.Category;
import com.cashub.cashhubbackend.cashub.domain.customer.Customer;
import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.domain.transaction.Transaction;
import com.cashub.cashhubbackend.cashub.dto.CustomerResponse;
import com.cashub.cashhubbackend.cashub.repository.AccountRepository;
import com.cashub.cashhubbackend.cashub.repository.CategoryRepository;
import com.cashub.cashhubbackend.cashub.repository.TransactionRepository;
import com.cashub.cashhubbackend.cashub.domain.account.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public Account createAccount(Account account, Long customerId) throws PaymentException {
        CustomerResponse customerResponse = customerService.retrieveCustomer(String.valueOf(customerId));
        Customer customer = new Customer(customerResponse.id(), customerResponse.email(), customerResponse.created());
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
        return accountOptional.get();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
        accountRepository.deleteById(id);
    }

    public Transaction createTransaction(Transaction transaction, Long accountId, Long categoryId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (accountOptional.isEmpty() || categoryOptional.isEmpty()) {
            throw new RuntimeException("Account or Category not found");
        }
        transaction.setAccount(accountOptional.get());
        transaction.setCategory(categoryOptional.get());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // Additional methods specific to account management can be added here
    // For example, deposit, withdraw, transfer functionalities etc.
}
