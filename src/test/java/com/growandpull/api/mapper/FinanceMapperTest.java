package com.growandpull.api.mapper;

import com.growandpull.api.dto.FinanceDto;
import com.growandpull.api.model.Currency;
import com.growandpull.api.model.Finance;
import com.growandpull.api.model.InvestmentType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinanceMapperTest {

    private final FinanceMapper underTest = Mappers.getMapper(FinanceMapper.class);

    @Test
    void testFinanceToDto(){
        // Arrange
        Finance finance = new Finance(BigDecimal.TEN, BigDecimal.ONE, Currency.EUR, InvestmentType.MULTI_INVESTOR);
        // Act
        FinanceDto financeDto = underTest.financeToDto(finance);
        // Assert
        assertEquals(finance.getRequiredAmount(), financeDto.requiredAmount());
        assertEquals(finance.getMinimumInvestmentAmount(), financeDto.minimumInvestmentAmount());
        assertEquals(finance.getCurrency(), financeDto.currency());

    }


}