package com.growandpull.api.controller;

import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.user.*;
import com.growandpull.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PutMapping("{userId}")
    public ResponseEntity<PrivateProfile> updateUser(@PathVariable("userId") String userId,
                                                     @Valid @RequestBody UserUpdateRequest userUpdateRequest,
                                                     @AuthenticationPrincipal UserDetails user) throws java.io.IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.updateUser(userId, userUpdateRequest, user.getUsername()));

    }

    @PutMapping("/updatePassword")
    public ResponseEntity<AuthenticationResponse> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest,
                                                                 @AuthenticationPrincipal UserDetails userDetails) {
        AuthenticationResponse updatedAuthentication = userService.updatePassword(userDetails, passwordUpdateRequest);
        return ResponseEntity.ok(updatedAuthentication);

    }

    @GetMapping("{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable("userId") String userId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Profile profile = userService.getProfile(userId, userDetails.getUsername());
        return ResponseEntity.ok(profile);

    }

    @GetMapping("update-data")
    public ResponseEntity<UserUpdateData> getUpdateData(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserUpdateData());
    }
}
