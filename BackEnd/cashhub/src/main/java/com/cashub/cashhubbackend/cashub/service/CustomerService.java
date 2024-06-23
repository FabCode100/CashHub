package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.CustomerRequest;
import com.cashub.cashhubbackend.cashub.dto.CustomerResponse;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private final StripePaymentGateway stripePaymentGateway;

    @Autowired
    public CustomerService(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public String createCustomer(String email) throws PaymentException {
        return stripePaymentGateway.createCustomer(email);
    }

    public void addPaymentMethodToCustomer(String paymentMethodId, String customerId) throws PaymentException {
        stripePaymentGateway.attachPaymentMethodToCustomer(paymentMethodId, customerId);
    }

    public CustomerResponse retrieveCustomer(String customerId) throws PaymentException {
        try {
            Customer customer = Customer.retrieve(customerId);
            return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getCreated());
        } catch (StripeException e) {
            throw new PaymentException("Failed to retrieve customer: " + e.getMessage());
        }
    }

    public void updateCustomerEmail(String customerId, String newEmail) throws PaymentException {
        try {
            Customer customer = Customer.retrieve(customerId);
            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put("email", newEmail);
            customer.update(updateParams);
        } catch (StripeException e) {
            throw new PaymentException("Failed to update customer email: " + e.getMessage());
        }
    }

    public void deleteCustomer(String customerId) throws PaymentException {
        try {
            Customer customer = Customer.retrieve(customerId);
            customer.delete();
        } catch (StripeException e) {
            throw new PaymentException("Failed to delete customer: " + e.getMessage());
        }
    }
}
