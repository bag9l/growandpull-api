package com.growandpull.api.dto.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
