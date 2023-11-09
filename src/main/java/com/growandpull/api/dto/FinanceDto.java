package com.growandpull.api.dto;

import com.growandpull.api.model.Currency;
import com.growandpull.api.model.InvestmentType;

import java.math.BigDecimal;

public record FinanceDto(
        BigDecimal collectedAmount,
        BigDecimal requiredAmount,
        BigDecimal minimumInvestmentAmount,
        Currency currency,
        InvestmentType investmentType
) {
}
