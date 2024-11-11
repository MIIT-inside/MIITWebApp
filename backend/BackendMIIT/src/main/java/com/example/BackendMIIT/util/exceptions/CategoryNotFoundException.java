package com.example.BackendMIIT.util.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String category) {
        super("Category not found: " + category);
    }
}
