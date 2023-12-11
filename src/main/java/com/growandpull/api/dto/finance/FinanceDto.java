package com.growandpull.api.dto.finance;

import com.growandpull.api.model.enums.Currency;

import java.math.BigDecimal;

public record FinanceDto(
        BigDecimal amount,
        Currency currency
) {
}
