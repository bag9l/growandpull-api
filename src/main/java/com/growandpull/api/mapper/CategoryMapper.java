package com.growandpull.api.mapper;

import com.growandpull.api.dto.category.CategoryRequest;
import com.growandpull.api.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract Category categoryToCategoryRequest(CategoryRequest categoryRequest);

    public abstract void updateCategoryFromRequest(CategoryRequest request, @MappingTarget Category category);

}
