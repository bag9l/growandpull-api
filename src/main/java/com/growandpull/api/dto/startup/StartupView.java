package com.growandpull.api.dto.startup;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.model.enums.StartupStatus;

import java.time.LocalDateTime;

public record StartupView(
        String title,
        UserCard owner,
        byte[] image,
        String description,
        StartupStatus status,
        String category,
        FinanceDto finance,
        LocalDateTime createdAt
) {
}
