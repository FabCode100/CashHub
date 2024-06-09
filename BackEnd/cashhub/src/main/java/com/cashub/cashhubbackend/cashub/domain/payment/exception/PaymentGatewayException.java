package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class PaymentGatewayException extends PaymentException {

    public PaymentGatewayException() {
        super();
    }

    public PaymentGatewayException(String message) {
        super(message);
    }

    public PaymentGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
