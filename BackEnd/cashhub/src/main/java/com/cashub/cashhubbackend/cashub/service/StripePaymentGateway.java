package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway {

    public StripePaymentGateway(@Value("${stripe.apiKey}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public Charge charge(PaymentRequest paymentRequest) throws PaymentException {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", paymentRequest.amount() * 100);
            chargeParams.put("currency", "usd");
            chargeParams.put("source", paymentRequest.token());
            return Charge.create(chargeParams);
        } catch (StripeException e) {
            throw new PaymentException("Payment failed: " + e.getMessage());
        }
    }

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException {
        return null;
    }
}
