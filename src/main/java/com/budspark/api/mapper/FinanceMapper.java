package com.budspark.api.mapper;

import com.budspark.api.dto.FinanceDto;
import com.budspark.api.model.Finance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FinanceMapper {

    public abstract FinanceDto financeToDto(Finance finance);
}
