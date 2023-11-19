package com.growandpull.api.service.impl;

import com.growandpull.api.dto.category.CategoryRequest;
import com.growandpull.api.exception.NotFoundException;
import com.growandpull.api.mapper.CategoryMapper;
import com.growandpull.api.model.Category;
import com.growandpull.api.repository.CategoryRepository;
import com.growandpull.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(String categoryId, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        categoryMapper.updateCategoryFromRequest(request, existingCategory);
        return categoryRepository.save(existingCategory);

    }


    @Override
    public Category createCategory(CategoryRequest categoryRequest) throws Exception {
        Category category = categoryMapper.categoryToCategoryRequest(categoryRequest);
        return categoryRepository.save(category);
    }


    @Override
    public void delete(String categoryId) {
        categoryRepository.deleteById(categoryId);

    }
}
