package com.growandpull.api.controller;

import com.growandpull.api.dto.PasswordUpdateRequest;
import com.growandpull.api.dto.Profile;
import com.growandpull.api.dto.ProfileView;
import com.growandpull.api.dto.UserUpdateRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.model.User;
import com.growandpull.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<ProfileView> updateUser(@PathVariable("userId") String userId,
                                                  @Valid @RequestBody UserUpdateRequest userUpdateRequest,
                                                  @AuthenticationPrincipal UserDetails user) throws java.io.IOException {

        ProfileView updatedProfile = userService.updateUser(userId, userUpdateRequest, user.getUsername());
        return ResponseEntity.ok(updatedProfile);

    }

    @PutMapping("updatePassword")
    public ResponseEntity<AuthenticationResponse> updatePassword(
                                                                 @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                                                 @AuthenticationPrincipal UserDetails userDetails) throws java.io.IOException {


        AuthenticationResponse updatedAuthentication = userService.updatePassword(userDetails, passwordUpdateRequest);
        return ResponseEntity.ok(updatedAuthentication);

    }

    @GetMapping("getProfile/{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable("userId") String userId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserById(userId);
        Profile profile = userService.getProfile(userDetails.getUsername(), user.getUsername());
        return ResponseEntity.ok(profile);

    }
}
