package com.growandpull.api.dto.user;

import com.growandpull.api.dto.location.CityDto;
import com.growandpull.api.model.enums.Degree;

import java.util.List;

public record UserUpdateData(
        List<Degree> educationDegrees,
        List<CityDto> cities
) {
}
