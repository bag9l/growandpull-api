package com.growandpull.api.mapper;

import com.growandpull.api.dto.CategoryDto;
import com.growandpull.api.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract Category categoryToCategoryRequest(CategoryDto categoryRequest);

    public abstract void updateCategoryFromRequest(CategoryDto request, @MappingTarget Category category);

}
