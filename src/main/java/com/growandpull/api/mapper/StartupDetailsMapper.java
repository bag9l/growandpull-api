package com.growandpull.api.mapper;

import com.growandpull.api.dto.startup.StartupDetailsDto;
import com.growandpull.api.model.StartupDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StartupDetailsMapper {
    public abstract StartupDetailsDto startupDetailsToDto(StartupDetails startupDetails);

    public abstract StartupDetails dtoToStartupDetails(StartupDetailsDto startupDetailsDto);


}
