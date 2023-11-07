package com.growandpull.api.dto;

public record PasswordUpdateRequest(
        String oldPassword,
        String newPassword
) {
}
