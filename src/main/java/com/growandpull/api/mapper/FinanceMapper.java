package com.growandpull.api.mapper;

import com.growandpull.api.dto.FinanceDto;
import com.growandpull.api.model.Finance;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FinanceMapper {

    public abstract FinanceDto financeToDto(Finance finance);
}