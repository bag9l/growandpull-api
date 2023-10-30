package com.budspark.api.dto;

import com.budspark.api.model.ExistenceTime;

public record StartupCard(
        String id,
        String title,
        byte[] image,
        String category,
        String description,
        ExistenceTime existenceTime
) {
}
