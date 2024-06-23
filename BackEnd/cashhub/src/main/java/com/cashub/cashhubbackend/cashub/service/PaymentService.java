package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.payment.Payment;
import com.cashub.cashhubbackend.cashub.domain.payment.exception.*;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.cashub.cashhubbackend.cashub.repository.PaymentRepository;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;
    private final EncryptionService encryptionService;
    private final TokenizationService tokenizationService;
    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(PaymentRepository paymentRepository, PaymentGateway paymentGateway,
                          EncryptionService encryptionService, TokenizationService tokenizationService) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
        this.encryptionService = encryptionService;
        this.tokenizationService = tokenizationService;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void createPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.amount());
        paymentRepository.save(payment);
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) throws PaymentException {
        logger.info("Iniciando processamento do pagamento");
        try {
            validatePaymentDetails(paymentRequest);

            // Encrypt card details
            String encryptedCardNumber = encryptionService.encrypt(paymentRequest.cardNumber());
            String encryptedCvv = encryptionService.encrypt(paymentRequest.cvv());

            // Tokenize card details
            String cardNumberToken = tokenizationService.tokenize(encryptedCardNumber);
            String cvvToken = tokenizationService.tokenize(encryptedCvv);

            // Create a new PaymentRequest with the tokenized values
            PaymentRequest secureRequest = new PaymentRequest(
                    cardNumberToken,
                    paymentRequest.expirationDate(),
                    cvvToken,
                    paymentRequest.amount(),
                    paymentRequest.cardType(),
                    null
            );

            Charge charge = paymentGateway.charge(secureRequest);
            return new PaymentResponse(true, "Pagamento realizado com sucesso", charge.getId());
        } catch (CreditCardNumberInvalidException e) {
            throw new CreditCardNumberInvalidException();
        } catch (ExpiredCardException e) {
            throw new ExpiredCardException();
        } catch (InvalidCvvException e) {
            throw new InvalidCvvException();
        } catch (InvalidPaymentAmountException e) {
            throw new InvalidPaymentAmountException();
        } catch (PaymentGatewayException e) {
            throw new PaymentGatewayException();
        } catch (Exception e) {
            throw new PaymentException("Erro desconhecido: " + e.getMessage());
        }
    }

    private void validatePaymentDetails(PaymentRequest request) throws PaymentException {
        List<String> errors = new ArrayList<>();
        logger.info("Iniciando validação dos detalhes do pagamento");

        // Card Number Validation (more comprehensive)
        try {
            if (!isValidCardNumber(request.cardNumber())) {
                errors.add("Invalid card number format or checksum");
            }
        } catch (CreditCardNumberInvalidException e) {
            errors.add(e.getMessage());
        }

        // Expiry Date Validation
        try {
            if (!isValidExpiryDate(request.expirationDate())) {
                errors.add("Invalid expiry date format");
            }
        } catch (ExpiredCardException e) {
            errors.add(e.getMessage());
        }

        // CVV Validation
        try {
            if (!isValidCvv(request.cvv())) {
                errors.add("Invalid CVV for card type");
            }
        } catch (InvalidCvvException e) {
            errors.add(e.getMessage());
        }

        // Amount Validation
        if (request.amount() <= 0) {
            errors.add(new InvalidPaymentAmountException().getMessage());
        }

        if (!errors.isEmpty()) {
            String errorMessage = String.join(", ", errors);
            logger.error("Validation errors: {}", errorMessage);
            throw new PaymentException(errorMessage);
        }

        logger.info("Validação dos detalhes do pagamento concluída");
    }

    private boolean isValidCardNumber(String cardNumber) throws CreditCardNumberInvalidException {
        // Verifique se o número do cartão não está vazio e se contém apenas dígitos
        if (cardNumber == null || !cardNumber.matches("[0-9]+")) {
            throw new CreditCardNumberInvalidException();
        }

        // Verifique se o número do cartão tem um comprimento válido
        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            throw new CreditCardNumberInvalidException();
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        if (sum % 10 != 0) {
            throw new CreditCardNumberInvalidException();
        }
        return true;
    }

    private boolean isValidExpiryDate(String expiryDate) throws ExpiredCardException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/yy");
            format.setLenient(false);
            Date parsedDate = format.parse(expiryDate);
            if (isFutureDate(parsedDate)) {
                return true; // A data é futura, não está expirada
            } else {
                throw new ExpiredCardException("Cartão expirado");
            }
        } catch (ParseException e) {
            // Se ocorrer uma ParseException, a data fornecida não está no formato esperado
            throw new ExpiredCardException("Formato de data de validade inválido");
        }
    }

    private boolean isValidCvv(String cvv) throws InvalidCvvException {
        int cvvLength = cvv.length();
        if (cvvLength != 3 && cvvLength != 4) { // Adjust based on card type acceptance
            throw new InvalidCvvException("Invalid CVV length");
        }
        if (!cvv.matches("[0-9]+")) { // Ensure numeric characters only
            throw new InvalidCvvException("Invalid CVV format");
        }
        // CVV validation successful
        return true;
    }

    private boolean isFutureDate(Date expiryDate) {
        Date currentDate = new Date();
        return expiryDate.after(currentDate);
    }
}