package com.growandpull.api.service;

import com.growandpull.api.dto.CategoryDto;
import com.growandpull.api.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category updateCategory(String categoryId, CategoryDto request);

    Category createCategory(CategoryDto category);

    void delete(String categoryId);
}
