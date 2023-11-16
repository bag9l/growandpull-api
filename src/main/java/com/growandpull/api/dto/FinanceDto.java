package com.growandpull.api.dto;

import com.growandpull.api.model.Currency;

import java.math.BigDecimal;

public record FinanceDto(
        BigDecimal amount,
        Currency currency
) {
}
