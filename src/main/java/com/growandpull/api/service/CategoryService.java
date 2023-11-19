package com.growandpull.api.service;

import com.growandpull.api.dto.category.CategoryRequest;
import com.growandpull.api.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category updateCategory(String categoryId, CategoryRequest request);

    Category createCategory(CategoryRequest category) throws Exception;

    void delete(String categoryId);
}
