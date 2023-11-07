package com.growandpull.api.dto;

import java.math.BigDecimal;

public record FinanceUpdate(
        BigDecimal minimumInvestmentAmount
) {
}
