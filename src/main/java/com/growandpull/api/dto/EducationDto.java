package com.growandpull.api.dto;

import com.growandpull.api.model.enums.Degree;

import java.time.LocalDate;

public record EducationDto(
        String school,
        String specialty,
        Degree degree,
        LocalDate startedAt,
        LocalDate graduatedAt
) {
}
