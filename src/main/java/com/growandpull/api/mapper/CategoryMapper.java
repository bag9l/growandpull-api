package com.growandpull.api.mapper;

import com.growandpull.api.dto.category.CategoryRequest;
import com.growandpull.api.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper {

    public abstract Category categoryToCategoryRequest(CategoryRequest categoryRequest);

}
