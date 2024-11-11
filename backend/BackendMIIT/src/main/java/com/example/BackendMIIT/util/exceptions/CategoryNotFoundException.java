package com.example.BackendMIIT.util.exceptions;

import com.example.BackendMIIT.model.domain.Category;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(Category category) {
      super("Category not found: " + category.getValue());
    }
}
