package com.growandpull.api.dto;

import java.time.LocalDate;


public record PublicProfile
        (String fullName,
         LocalDate birth,
         String description,
         byte[] avatarImageData) {

}
