package com.growandpull.api.dto;

public record PasswordUpdate(
        String oldPassword,
        String newPassword
) {
}
