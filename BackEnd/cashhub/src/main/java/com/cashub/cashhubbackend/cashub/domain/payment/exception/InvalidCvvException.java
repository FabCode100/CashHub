package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class InvalidCvvException extends PaymentException {
    public InvalidCvvException() {
        super();
    }

    public InvalidCvvException(String message) {
        super(message);
    }

    public InvalidCvvException(String message, Throwable cause) {
        super(message, cause);
    }
}
