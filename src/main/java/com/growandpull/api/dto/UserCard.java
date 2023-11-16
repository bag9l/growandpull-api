package com.growandpull.api.dto;

public record UserCard(
        String id,
        String firstName,
        String lastName,
        byte[] avatar
) {
}
