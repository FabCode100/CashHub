package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class PaymentAuthorizationException extends PaymentException {

    public PaymentAuthorizationException() {
        super();
    }

    public PaymentAuthorizationException(String message) {
        super(message);
    }

    public PaymentAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
