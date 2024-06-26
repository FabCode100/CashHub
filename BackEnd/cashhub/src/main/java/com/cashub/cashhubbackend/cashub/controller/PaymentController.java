package com.cashub.cashhubbackend.cashub.controller;

import com.cashub.cashhubbackend.cashub.domain.payment.Payment;
import com.cashub.cashhubbackend.cashub.domain.payment.exception.PaymentException;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/process")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {
        // Verifique se os campos obrigatórios não são nulos
        if (request.cardNumber() == null || request.cardNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número do cartão é obrigatório");
        }
        if (request.expirationDate() == null || request.expirationDate().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de expiração é obrigatória");
        }
        if (request.cvv() == null || request.cvv().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CVV é obrigatório");
        }
        if (request.amount() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor é obrigatório");
        }

        // Processe o pagamento aqui
        try {
            paymentService.processPayment(request);
            ResponseEntity.status(HttpStatus.valueOf(200));
            return ResponseEntity.ok("Pagamento realizado com sucesso");
        } catch (Exception e) {
            e.printStackTrace(); // Registre o erro para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro desconhecido: " + e.getMessage());
        }
    }
}
