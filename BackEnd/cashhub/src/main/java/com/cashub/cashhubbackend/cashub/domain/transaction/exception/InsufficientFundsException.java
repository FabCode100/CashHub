package com.cashub.cashhubbackend.cashub.domain.transaction.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
