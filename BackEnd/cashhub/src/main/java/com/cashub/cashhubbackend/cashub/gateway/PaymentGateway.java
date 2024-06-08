package com.cashub.cashhubbackend.cashub.gateway;

import com.cashub.cashhubbackend.cashub.domain.payment.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.stripe.model.Charge;

public interface PaymentGateway {
    
    PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException;

    Charge charge(PaymentRequest paymentRequest);
}
