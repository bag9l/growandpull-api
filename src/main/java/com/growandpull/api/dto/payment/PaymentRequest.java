package com.growandpull.api.dto.payment;

import com.growandpull.api.model.Currency;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        Currency currency
) {
}
