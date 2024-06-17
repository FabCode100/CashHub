package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.category.Category;
import com.cashub.cashhubbackend.cashub.repository.CategoryRepository;
import com.cashub.cashhubbackend.cashub.domain.category.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        // 1. Save category to database
        return categoryRepository.save(category);
    }

    public Category getCategory(Long id) {
        // 1. Find category by id
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        // 2. Check if category exists
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        // 3. Return category
        return categoryOptional.get();
    }

    public List<Category> getAllCategories() {
        // 1. Find all categories
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category) {
        // 1. Find category by id (optional, can be done based on the category object itself)
        Long categoryId = category.getId();
        // Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);

        // 2. Update category details
        // ... (you can update specific fields based on your needs)

        // 3. Save updated category to database
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        // 1. Find category by id
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        // 2. Check if category exists
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        // 3. Delete category (consider handling associated transactions)
        categoryRepository.deleteById(id);
    }

    // Additional methods specific to category management can be added here
    // For example, find category by name etc.
}
