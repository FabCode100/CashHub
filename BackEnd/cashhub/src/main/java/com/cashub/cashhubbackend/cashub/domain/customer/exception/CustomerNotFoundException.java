package com.cashub.cashhubbackend.cashub.domain.customer.exception;

public class CustomerNotFoundException extends RuntimeException {

    private Long userId;

    public CustomerNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
