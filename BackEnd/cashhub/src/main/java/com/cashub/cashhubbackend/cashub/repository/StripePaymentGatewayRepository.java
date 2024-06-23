package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.stripe.model.Charge;

public interface StripePaymentGatewayRepository {
    Charge charge(PaymentRequest paymentRequest) throws PaymentException;

    PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException;
}
