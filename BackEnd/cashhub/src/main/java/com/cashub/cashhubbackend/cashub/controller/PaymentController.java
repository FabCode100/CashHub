package com.cashub.cashhubbackend.cashub.controller;

import com.cashub.cashhubbackend.cashub.domain.payment.Payment;
import com.cashub.cashhubbackend.cashub.dto.PaymentRequest;
import com.cashub.cashhubbackend.cashub.dto.PaymentResponse;
import com.cashub.cashhubbackend.cashub.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) throws StripeException {
        String response = String.valueOf(paymentService.processPayment(request));
        return ResponseEntity.ok(response);
    }
}
