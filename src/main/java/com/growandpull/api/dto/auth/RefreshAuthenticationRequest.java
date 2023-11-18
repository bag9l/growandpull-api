package com.growandpull.api.dto.auth;

public record RefreshAuthenticationRequest(
        String refreshToken
) {
}
