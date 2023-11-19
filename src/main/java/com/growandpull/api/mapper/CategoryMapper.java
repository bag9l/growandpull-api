package com.growandpull.api.mapper;

import com.growandpull.api.dto.category.CategoryCreationRequest;
import com.growandpull.api.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract Category categoryToCategoryRequest(CategoryCreationRequest categoryRequest);

    public abstract void updateCategoryFromRequest(CategoryCreationRequest request, @MappingTarget Category category);

}
