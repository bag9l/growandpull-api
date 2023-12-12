package com.growandpull.api.dto.subscription;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;

public record SubscriptionCard(
        SubscriptionTypeIdentifier id,
        String name,
        FinanceDto price
) {
}
