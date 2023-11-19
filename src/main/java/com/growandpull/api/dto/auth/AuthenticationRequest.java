package com.growandpull.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @Email
        String email,
        @Size(min = 8, max = 32, message = "password length should be minimum 8 and maximum 32 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[a-zA-Z\\d\\W_]+$",
        message = "password should contain at least: one capital letter, one small letter, one digit")
        String password
) {
}
