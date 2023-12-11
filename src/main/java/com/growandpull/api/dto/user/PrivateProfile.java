package com.growandpull.api.dto.user;

import com.growandpull.api.dto.education.EducationDto;
import com.growandpull.api.dto.location.CityDto;

import java.time.LocalDate;

public record PrivateProfile(
        String firstName,
        String lastName,
        LocalDate birthday,
        CityDto city,
        String about,
        String email,
        String phoneNumber,
        byte[] avatar,
        EducationDto education,
        String experience) implements Profile {
}


