package com.cashub.cashhubbackend.cashub.domain.account.exception;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {

    private Long accountId;

    public AccountNotFoundException(Long accountId) {
        super("Account not found with ID: " + accountId);
        this.accountId = accountId;
    }

}
