package com.growandpull.api.dto.profile;

import java.time.LocalDate;


public record PublicProfile
        (String firstName,
         String lastName,
         LocalDate birth,
         String aboutUser,
         byte[] avatar) implements Profile{

}
