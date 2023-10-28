package com.budspark.api.dto.auth;

public record AuthenticatedUser(
        String login,
        String fullName,
        String email,
        String role,
        byte[] avatar
) {
}
