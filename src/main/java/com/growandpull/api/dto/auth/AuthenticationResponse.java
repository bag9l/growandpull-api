package com.growandpull.api.dto.auth;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken) {
}
