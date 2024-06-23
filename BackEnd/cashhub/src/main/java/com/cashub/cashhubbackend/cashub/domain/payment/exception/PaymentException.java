package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException() {
        super();
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}

