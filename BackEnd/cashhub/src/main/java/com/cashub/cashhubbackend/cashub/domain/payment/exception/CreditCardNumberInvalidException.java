package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class CreditCardNumberInvalidException extends PaymentException {

    public CreditCardNumberInvalidException() {
        super();
    }

    public CreditCardNumberInvalidException(String message) {
        super(message);
    }

    public CreditCardNumberInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
