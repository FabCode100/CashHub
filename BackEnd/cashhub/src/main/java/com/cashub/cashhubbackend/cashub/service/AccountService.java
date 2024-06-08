package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.repository.AccountRepository;
import com.cashub.cashhubbackend.cashub.domain.user.User;
import com.cashub.cashhubbackend.cashub.service.TransactionService; // Assuming TransactionService exists
import com.cashub.cashhubbackend.cashub.service.UserService; // Assuming UserService exists
import com.cashub.cashhubbackend.cashub.domain.account.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository; // Assuming AccountRepository is available

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account, Long userId) {
        // 1. Find user by userId
        User user = userService.getUser(userId);

        // 2. Set user association for account
        account.setUser(user);

        // 3. Save account to database
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        // 1. Find account by id
        Optional<Account> accountOptional = accountRepository.findById(id);

        // 2. Check if account exists
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException(id);
        }

        // 3. Return account
        return accountOptional.get();
    }

    public List<Account> getAllAccounts() {
        // 1. Find all accounts
        return accountRepository.findAll();
    }

    public Account updateAccount(Account account) {
        // 1. Find account by id (optional, can be done based on the account object itself)
        Long accountId = account.getId();
        // Optional<Account> existingAccountOptional = accountRepository.findById(accountId);

        // 2. Update account details
        // ... (you can update specific fields based on your needs)

        // 3. Save updated account to database
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        // 1. Find account by id
        Optional<Account> accountOptional = accountRepository.findById(id);

        // 2. Check if account exists
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException(id);
        }

        // 3. Delete account (consider handling transactions associated with the account)
        accountRepository.deleteById(id);
    }

    // Additional methods specific to account management can be added here
    // For example, deposit, withdraw, transfer functionalities etc.
    // Remember to consider transaction service and balance updates while implementing these methods.
}
