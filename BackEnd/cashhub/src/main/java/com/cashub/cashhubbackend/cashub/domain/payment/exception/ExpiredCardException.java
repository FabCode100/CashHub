package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class ExpiredCardException extends PaymentException {

    public ExpiredCardException() {
        super();
    }

    public ExpiredCardException(String message) {
        super(message);
    }

    public ExpiredCardException(String message, Throwable cause) {
        super(message, cause);
    }
}
