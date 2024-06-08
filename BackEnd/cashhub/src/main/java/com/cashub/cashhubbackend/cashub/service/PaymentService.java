package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.payment.CreditCardNumberInvalidException;
import com.cashub.cashhubbackend.cashub.domain.payment.PaymentGatewayException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.domain.payment.PaymentException;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.cashub.cashhubbackend.cashub.domain.payment.PaymentAuthorizationException;
import com.cashub.cashhubbackend.cashub.domain.payment.ExpiredCardException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;
    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        try {
            validatePaymentDetails(paymentRequest);
            Charge charge = paymentGateway.charge(paymentRequest);
            return new PaymentResponse(true, "Pagamento realizado com sucesso", charge.getId());
        } catch (CreditCardNumberInvalidException e) {
            logger.error("Número de cartão de crédito inválido", e);
            return new PaymentResponse(false, "Número de cartão de crédito inválido", null);
        } catch (ExpiredCardException e) {
            logger.error("Cartão expirado", e);
            return new PaymentResponse(false, "Cartão expirado", null);
        } catch (PaymentAuthorizationException e) {
            logger.error("Pagamento recusado: " + e.getMessage(), e);
            return new PaymentResponse(false, "Pagamento recusado: " + e.getMessage(), null);
        } catch (PaymentGatewayException e) {
            logger.error("Erro de comunicação com o gateway de pagamento", e);
            return new PaymentResponse(false, "Erro de comunicação com o gateway de pagamento", null);
        } catch (Exception e) {
            logger.error("Erro interno do sistema durante o processamento do pagamento", e);
            return new PaymentResponse(false, "Erro interno do sistema. Tente novamente mais tarde.", null);
        } catch (PaymentException e) {
            throw new RuntimeException(e);
        }
    }

    private void validatePaymentDetails(PaymentRequest request) throws PaymentException {
        List<String> errors = new ArrayList<>();

        // Card Number Validation (more comprehensive)
        if (!isValidCardNumber(request.cardNumber())) {
            errors.add("Invalid card number format or checksum");
        }

        // Expiry Date Validation
        if (!isValidExpiryDate(request.expirationDate())) {
            errors.add("Invalid expiry date format or expired card");
        }

        // CVV Validation
        if (!isValidCvv(request.cvv(), request.cardType())) { // Consider card type
            errors.add("Invalid CVV for card type");
        }
        // Amount Validation
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Invalid payment amount");
        }

        if (errors.isEmpty()) {
            // Process payment using Stripe or other payment gateway

            ResponseEntity.ok(new PaymentResponse(true, "Payment successful", null));
        } else {
            ResponseEntity.badRequest().body(new PaymentResponse(false, "Validation errors", null));
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private boolean isValidExpiryDate(String expiryDate) throws PaymentException{
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/yy"); // Adjust format based on your input
            format.setLenient(false); // Ensures strict parsing
            Date parsedDate = format.parse(expiryDate);
            return isFutureDate(parsedDate);
        } catch (ParseException e) {
            throw new PaymentException("Invalid expiry date format");
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFutureDate(Date expiryDate) {
        Date currentDate = new Date();
        return expiryDate.after(currentDate);
    }

    private boolean isValidCvv(String cvv, String cardType) {
        int cvvLength = cvv.length();
        if (cvvLength != 3 && cvvLength != 4) { // Adjust based on card type acceptance
            throw new PaymentException("Invalid CVV length");
        }
        if (!cvv.matches("[0-9]+")) { // Ensure numeric characters only
            throw new PaymentException("Invalid CVV format");
        }
        // CVV validation successful, return true;
        return true;
    }
}
