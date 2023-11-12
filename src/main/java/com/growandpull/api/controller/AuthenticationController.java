package com.growandpull.api.controller;

import com.growandpull.api.dto.ProfileView;
import com.growandpull.api.dto.StartupUpdateRequest;
import com.growandpull.api.dto.StartupView;
import com.growandpull.api.dto.UserUpdateRequest;
import com.growandpull.api.dto.auth.AuthenticatedUser;
import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.auth.RegisterRequest;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService userService;

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

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<ProfileView> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UserUpdateRequest userUpdateRequest
    ) throws java.io.IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserId = authentication.getName();

        if (!authenticatedUserId.equals(userId)) {
            throw new PermissionException("You do not have permission to update this profile");
        }

        try {
            ProfileView updatedProfile = userService.updateUser(userId, userUpdateRequest);
            return ResponseEntity.ok(updatedProfile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
