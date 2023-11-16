package com.growandpull.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email
        String email,
        @Size(min = 8, max = 32, message = "password length should be minimum 8 and maximum 32 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]+$",
                message = "password should contain at least: one capital letter, one small letter, one digit, and one special symbol")
        String password,
        @NotBlank(message = "first name should not be empty")
        @Size(min = 2, max = 100, message = "first name length should be minimum 2 and maximum 100 characters")
        String firstName,
        @NotBlank(message = "last name should not be empty")
        @Size(min = 2, max = 100, message = "last name length should be minimum 2 and maximum 100 characters")
        String lastName
) {
}