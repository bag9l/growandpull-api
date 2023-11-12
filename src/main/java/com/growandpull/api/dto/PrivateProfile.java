package com.growandpull.api.dto;

import com.growandpull.api.model.Avatar;

import java.time.LocalDate;

public record PrivateProfile
        (String fullName,
         LocalDate birth,
         String description,
         byte[] avatar) {

}


