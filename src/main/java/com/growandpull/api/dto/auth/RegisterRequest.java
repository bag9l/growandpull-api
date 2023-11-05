package com.growandpull.api.dto.auth;

public record RegisterRequest(
        String login,
        String password,
        String fullName,
        String email
) {
}