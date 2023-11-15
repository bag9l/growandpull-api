package com.growandpull.api.dto;

import java.time.LocalDate;


public record PublicProfile
        (String fullName,
         LocalDate birth,
         String aboutUser,
         byte[] avatar) implements Profile{

}
