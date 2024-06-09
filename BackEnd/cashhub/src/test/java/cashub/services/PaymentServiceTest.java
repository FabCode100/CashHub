package cashub.services;

import com.cashub.cashhubbackend.cashub.domain.payment.exception.*;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.gateway.PaymentGateway;
import com.cashub.cashhubbackend.cashub.repository.PaymentRepository;
import com.cashub.cashhubbackend.cashub.service.EncryptionService;
import com.cashub.cashhubbackend.cashub.service.PaymentService;
import com.cashub.cashhubbackend.cashub.service.TokenizationService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;

public class PaymentServiceTest {

    @Test
    void testSuccessfulPayment() throws Exception {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("validCardNumber", "expiryDate", "cvv", 10.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");

        // Act
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Assert
        assertTrue(paymentResponse.isSuccess());
    }

    @Test
    void testInvalidCardNumber() throws Exception, PaymentException {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("invalidCardNumber", "08/25", "123", 10.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");
        doThrow(new CreditCardNumberInvalidException()).when(paymentGateway).charge(any());

        // Act
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Assert
        assertFalse(paymentResponse.isSuccess());
        assertEquals("Número de cartão de crédito inválido", paymentResponse.message());
    }

    @Test
    void testExpiredCard() throws Exception, PaymentException {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("validCardNumber", "11/23", "cvv", 10.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");
        doThrow(new ExpiredCardException()).when(paymentGateway).charge(any());

        // Act
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Assert
        assertFalse(paymentResponse.isSuccess());
        assertEquals("Cartão expirado", paymentResponse.message());
    }

    @Test
    void testInvalidCvv() throws Exception {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("validCardNumber", "expiryDate", "invalidCvv", 100.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");

        // Act & Assert
        PaymentException exception = assertThrows(PaymentException.class, () -> {
            paymentService.processPayment(paymentRequest);
        });

        assertTrue(exception.getMessage().contains("Invalid CVV"));
    }


    @Test
    void testInvalidPaymentAmount() throws Exception {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("validCardNumber", "expiryDate", "cvv", 0.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");

        // Act
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Assert
        assertFalse(paymentResponse.isSuccess());
        assertEquals("Invalid payment amount", paymentResponse.message());
    }

    @Test
    void testPaymentGatewayCommunicationError() throws Exception, PaymentException {
        // Arrange
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        EncryptionService encryptionService = mock(EncryptionService.class);
        TokenizationService tokenizationService = mock(TokenizationService.class);
        PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway, encryptionService, tokenizationService);
        PaymentRequest paymentRequest = new PaymentRequest("validCardNumber", "expiryDate", "cvv", 10.0, "cardType", "token");

        when(encryptionService.encrypt(anyString())).thenReturn("encryptedValue");
        when(tokenizationService.tokenize(anyString())).thenReturn("tokenizedValue");
        doThrow(new PaymentGatewayException()).when(paymentGateway).charge(any());

        // Act
        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        // Assert
        assertFalse(paymentResponse.isSuccess());
        assertEquals("Erro de comunicação com o gateway de pagamento", paymentResponse.message());
    }
}
