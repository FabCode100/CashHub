package com.cashub.cashhubbackend.cashub.dto;

import java.math.BigDecimal;

public record PaymentRequest(String cardNumber, String expirationDate, String cvv, Double amount, String cardType, String token) {}
