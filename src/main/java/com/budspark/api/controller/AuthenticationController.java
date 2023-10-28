package com.budspark.api.controller;

import com.budspark.api.dto.auth.AuthenticatedUser;
import com.budspark.api.dto.auth.AuthenticationRequest;
import com.budspark.api.dto.auth.AuthenticationResponse;
import com.budspark.api.dto.auth.RegisterRequest;
import com.budspark.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping(path = "login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.authenticate(request)
        );
    }


    @PostMapping("success")
    public ResponseEntity<AuthenticatedUser> getAuthenticatedUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authenticationService.getAuthenticatedUser(user.getUsername()));
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

}