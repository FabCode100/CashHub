package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.cashub.cashhubbackend.cashub.repository.StripePaymentGatewayRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway, StripePaymentGatewayRepository {

    public StripePaymentGateway(@Value("${stripe.apiKey}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public Charge charge(PaymentRequest paymentRequest) throws PaymentException {
        try {
            // Usa o token gerado pelo frontend para criar uma cobrança
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (paymentRequest.amount() * 100)); // valor em centavos
            chargeParams.put("currency", "usd");
            chargeParams.put("source", paymentRequest.cardNumber());

            return Charge.create(chargeParams);
        } catch (StripeException e) {
            throw new PaymentException("Payment failed: " + e.getMessage());
        }
    }

    public String createCustomer(String email) throws PaymentException {
        try {
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("email", email);
            Customer customer = Customer.create(customerParams);
            return customer.getId();
        } catch (StripeException e) {
            throw new PaymentException("Failed to create customer: " + e.getMessage());
        }
    }

    public void attachPaymentMethodToCustomer(String paymentMethodId, String customerId) throws PaymentException {
        try {
            PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);
            paymentMethod.attach(Map.of("customer", customerId));
        } catch (StripeException e) {
            throw new PaymentException("Failed to attach payment method: " + e.getMessage());
        }
    }

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException {
        return null;
    }
}
