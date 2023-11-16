package com.growandpull.api.dto;

import java.time.LocalDate;

public record PrivateProfile
        (String firstName,
         String lastName,
         LocalDate birth,
         String email,
         String aboutUser,
         byte[] avatar) implements Profile {

}


