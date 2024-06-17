package com.cashub.cashhubbackend.cashub.domain.category.exception;

import lombok.Getter;

@Getter
public class CategoryNotFoundException extends RuntimeException {

    private final Long categoryId;

    public CategoryNotFoundException(Long categoryId) {
        super("Category not found with ID: " + categoryId);
        this.categoryId = categoryId;
    }

}

