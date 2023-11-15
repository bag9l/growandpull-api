package com.growandpull.api.dto.auth;

public record AuthenticatedUser(
        String email,
        String fullName,
        String role,
        byte[] avatar
) {
}
