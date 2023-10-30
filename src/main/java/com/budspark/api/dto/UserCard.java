package com.budspark.api.dto;

public record UserCard(
        String id,
        String fullName,
        byte[] avatar
) {
}
