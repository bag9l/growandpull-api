package com.growandpull.api.dto.user;

public record PasswordUpdateRequest(
        String oldPassword,
        String newPassword
) {
}
