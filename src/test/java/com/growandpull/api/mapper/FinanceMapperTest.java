package com.growandpull.api.mapper;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.model.enums.Currency;
import com.growandpull.api.model.entity.Finance;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class FinanceMapperTest {

    private final FinanceMapper underTest = Mappers.getMapper(FinanceMapper.class);

    @Test
    void testFinanceToDto(){
        // Arrange
        Finance finance = new Finance(BigDecimal.TEN, Currency.EUR);
        // Act
        FinanceDto financeDto = underTest.financeToDto(finance);
        // Assert
        assertEquals(finance.getAmount(), financeDto.amount());
        assertEquals(finance.getCurrency(), financeDto.currency());

    }


}