package com.growandpull.api.dto.auth;

public record AuthenticationRequest(
        String login,
        String password
) {
}
