package com.budspark.api.dto;

public record PasswordUpdate(
        String oldPassword,
        String newPassword
) {
}
