package com.growandpull.api.dto;

import com.growandpull.api.model.StartupStatus;
import org.springframework.web.multipart.MultipartFile;

public record NewStartup(
        String title,
        MultipartFile image,
        String description,
        StartupStatus status,
        String categoryId,
        FinanceDto finance
) {
}
