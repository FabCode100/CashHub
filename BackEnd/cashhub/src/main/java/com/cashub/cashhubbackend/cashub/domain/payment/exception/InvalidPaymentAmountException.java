package com.cashub.cashhubbackend.cashub.domain.payment.exception;

public class InvalidPaymentAmountException extends PaymentException {
    public InvalidPaymentAmountException() {
        super("Valor de pagamento inválido");
    }
}
