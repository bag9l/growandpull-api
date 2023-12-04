package com.growandpull.api.mapper;

import com.growandpull.api.dto.startup.StartupDetailsDto;
import com.growandpull.api.model.entity.StartupDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StartupDetailsMapper {
    public abstract StartupDetailsDto detailsToDto(StartupDetails details);
    public abstract StartupDetails dtoToDetails(StartupDetailsDto dto);
}
