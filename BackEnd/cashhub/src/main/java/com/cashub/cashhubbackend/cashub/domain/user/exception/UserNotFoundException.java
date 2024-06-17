package com.cashub.cashhubbackend.cashub.domain.user.exception;

public class UserNotFoundException extends RuntimeException {

    private Long userId;

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
