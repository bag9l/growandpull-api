package com.growandpull.api.controller;

import com.growandpull.api.dto.*;
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

import java.util.Objects;

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

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<ProfileView> updateUser(@PathVariable String userId,
                                                  @Valid @RequestBody UserUpdateRequest userUpdateRequest,
                                                  @AuthenticationPrincipal UserDetails userDetails) throws java.io.IOException {
        try {
            if (!userId.equals(userDetails.getUsername())) {
                throw new PermissionException("You do not have permission to update this profile");
            }

            ProfileView updatedProfile = userService.updateUser(userId, userUpdateRequest);
            return ResponseEntity.ok(updatedProfile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("updatePassword/{userId}")
    public ResponseEntity<AuthenticationResponse> updatePassword(
            @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest,
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable String userId) throws java.io.IOException {
        try {
            if (!userId.equals(userDetails.getUsername())) {
                throw new PermissionException("You do not have permission to update this password");
            }
            AuthenticationResponse updatedAuthentication = userService.updatePassword(userDetails, passwordUpdateRequest);
            return ResponseEntity.ok(updatedAuthentication);
        } catch (PermissionException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("profile/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable String userId,
                                        @AuthenticationPrincipal UserDetails userDetails,
                                        PublicProfile publicProfile,
                                        PrivateProfile privateProfile) {
        if (Objects.equals(userId, userDetails.getUsername())) {
            return ResponseEntity.ok(privateProfile);
        } else {
            return ResponseEntity.ok(publicProfile);
        }
    }


}
