package com.growandpull.api.dto;

import com.growandpull.api.model.StartupStatus;

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