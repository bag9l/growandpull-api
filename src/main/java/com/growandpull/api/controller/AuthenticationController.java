package com.growandpull.api.controller;

import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.auth.RefreshAuthenticationRequest;
import com.growandpull.api.dto.auth.RegisterRequest;
import com.growandpull.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerUser(request)
        );
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping("register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@Valid @RequestBody RegisterRequest request) {
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


}
