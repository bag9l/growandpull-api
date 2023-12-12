package com.growandpull.api.dto.subscription;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;

public record SubscriptionDto(
        String name,
        String description,
        SubscriptionTypeIdentifier type,
        FinanceDto price,
        String period
) {
}
