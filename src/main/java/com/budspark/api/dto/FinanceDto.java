package com.budspark.api.dto;

import com.budspark.api.model.Currency;

import java.math.BigDecimal;

public record FinanceDto(
        BigDecimal requiredAmount,
        BigDecimal minimumInvestmentAmount,
        Currency currency
) {
}
