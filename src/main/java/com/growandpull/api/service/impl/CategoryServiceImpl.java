package com.growandpull.api.service.impl;

import com.growandpull.api.dto.category.CategoryRequest;
import com.growandpull.api.exception.ConflictException;
import com.growandpull.api.exception.NotFoundException;
import com.growandpull.api.mapper.CategoryMapper;
import com.growandpull.api.model.Category;
import com.growandpull.api.repository.CategoryRepository;
import com.growandpull.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);
        if (existingCategoryOptional.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        Category existingCategory = existingCategoryOptional.get();
        String updatedCategoryName = request.getName();
        existingCategory.setName(updatedCategoryName);
        return categoryRepository.save(existingCategory);

    }


    @Override
    public Category save(CategoryRequest categoryRequest) throws Exception {
        Category category = categoryMapper.categoryToCategoryRequest(categoryRequest);
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }


    @Override
    public void delete(String categoryId) {
        categoryRepository.deleteById(categoryId);

    }
}
