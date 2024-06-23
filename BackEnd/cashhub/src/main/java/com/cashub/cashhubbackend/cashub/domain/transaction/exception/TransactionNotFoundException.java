package com.cashub.cashhubbackend.cashub.domain.transaction.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long transactionId) {
        super("Transaction not found with id: " + transactionId);
    }
}
