package com.growandpull.api.dto.user;

import com.growandpull.api.dto.EducationDto;
import com.growandpull.api.dto.location.CityDto;

import java.time.LocalDate;


public record PublicProfile(
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
