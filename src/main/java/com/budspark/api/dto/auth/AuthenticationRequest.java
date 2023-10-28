package com.budspark.api.dto.auth;

public record AuthenticationRequest(
        String login,
        String password
) {
}
