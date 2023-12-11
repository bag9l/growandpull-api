package com.growandpull.api.dto.startup;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.model.ExistenceTime;

public record StartupCard(
        String id,
        String title,
        byte[] image,
        String category,
        String description,
        FinanceDto finance,
        ExistenceTime existenceTime
) {
}
