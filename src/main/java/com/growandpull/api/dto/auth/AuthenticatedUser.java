package com.growandpull.api.dto.auth;

public record AuthenticatedUser(
        String email,
        String firstName,
        String lastName,
        String role,
        byte[] avatar
) {
}
