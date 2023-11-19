package com.growandpull.api.controller;

import com.growandpull.api.dto.category.CategoryCreationRequest;
import com.growandpull.api.model.Category;
import com.growandpull.api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryCreationRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.createCategory(request));
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") String categoryId) throws Exception {
        categoryService.delete(categoryId);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(
                categoryService.getAllCategories()
        );
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") String categoryId,
                                                   @RequestBody CategoryCreationRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.updateCategory(categoryId, categoryRequest));

    }
}
