package com.growandpull.api.dto.location;

public record CityDto(
        int id,
        String name,
        CountryDto country
) {
}
