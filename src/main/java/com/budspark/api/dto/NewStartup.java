package com.budspark.api.dto;

import com.budspark.api.model.StartupStatus;
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
