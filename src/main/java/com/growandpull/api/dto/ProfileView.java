package com.growandpull.api.dto;

import java.time.LocalDate;

public record ProfileView(String fullName,
                          LocalDate birth,
                          String login,
                          String email,
                          String aboutUser,
                          byte[] avatar) {
}
