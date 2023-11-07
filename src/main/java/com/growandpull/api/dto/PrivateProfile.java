package com.growandpull.api.dto;

import java.time.LocalDate;

public record PrivateProfile(String firstName,
                             String lastName,
                             String sex,
                             String email,
                             String phoneNumber,
                             LocalDate birth,
                             String description,
                             String password) {
}
