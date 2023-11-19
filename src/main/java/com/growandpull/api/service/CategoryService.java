package com.growandpull.api.service;

import com.growandpull.api.dto.category.CategoryCreationRequest;
import com.growandpull.api.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category updateCategory(String categoryId, CategoryCreationRequest request);

    Category createCategory(CategoryCreationRequest category);

    void delete(String categoryId);
}
