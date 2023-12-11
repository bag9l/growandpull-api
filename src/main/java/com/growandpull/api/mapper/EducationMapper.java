package com.growandpull.api.mapper;

import com.growandpull.api.dto.education.EducationDto;
import com.growandpull.api.model.entity.Education;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EducationMapper {

    public abstract EducationDto educationToDto(Education education);

    public abstract void dtoToEducation(EducationDto dto, @MappingTarget Education education);
}
