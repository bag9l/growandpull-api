package com.growandpull.api.dto;

import java.time.LocalDate;

public record ProfileView(String firstName,
                          String lastName,
                          LocalDate birth,
                          String email,
                          String aboutUser,
                          byte[] avatar) {
}
