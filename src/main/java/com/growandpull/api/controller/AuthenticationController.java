package com.growandpull.api.controller;

import com.growandpull.api.dto.auth.*;
import com.growandpull.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.authenticate(request)
        );
    }

    @PostMapping("register/user")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerUser(request)
        );
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping("register/admin")
    public ResponseEntity<Response> registerAdmin(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerAdmin(request)
        );
    }

    @PostMapping("refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshAuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.refreshToken(request)
        );
    }

    @PostMapping("/confirm-account/{token}")
    public AuthenticationResponse confirmUserAccount(@PathVariable("token") String confirmationToken) {
        return authenticationService.confirmEmail(confirmationToken);
    }

    @PostMapping("reset-password")
    public ResponseEntity<Response> sendResetPasswordEmail(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.sendResetPasswordEmail(email)
        );
    }

    @PostMapping("password/reset/{token}")
    public ResponseEntity<AuthenticationResponse> resetPasswordEmail(@PathVariable("token") String token,
                                                                     @RequestParam("password") String password) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.resetPassword(token, password)
        );
    }
}
