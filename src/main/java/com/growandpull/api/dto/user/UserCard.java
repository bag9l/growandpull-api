package com.growandpull.api.dto.user;

public record UserCard(
        String id,
        String firstName,
        String lastName,
        byte[] avatar
) {
}
