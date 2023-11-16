package com.growandpull.api.dto;

import java.time.LocalDate;


public record PublicProfile
        (String firstName,
         String lastName,
         LocalDate birth,
         String aboutUser,
         byte[] avatar) implements Profile{

}
