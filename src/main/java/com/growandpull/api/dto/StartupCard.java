package com.growandpull.api.dto;

import com.growandpull.api.model.ExistenceTime;

public record StartupCard(
        String id,
        String title,
        byte[] image,
        String category,
        String description,
        ExistenceTime existenceTime
) {
}
