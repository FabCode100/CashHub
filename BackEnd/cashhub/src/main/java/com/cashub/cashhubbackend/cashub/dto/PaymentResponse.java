package com.cashub.cashhubbackend.cashub.dto;

public record PaymentResponse(boolean success, String message, Object o) {
    public boolean isSuccess() {
        return false;
    }
}
