package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.account.Account;
import com.cashub.cashhubbackend.cashub.repository.AccountRepository;
import com.cashub.cashhubbackend.cashub.domain.account.exception.AccountNotFoundException;
import com.cashub.cashhubbackend.cashub.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public Account createAccount(Account account, Long userId) {
        User user = userService.getUser(userId);
        account.setUser(user);
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

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
        accountRepository.deleteById(id);
    }

    // Additional methods specific to account management can be added here
    // For example, deposit, withdraw, transfer functionalities etc.
}
