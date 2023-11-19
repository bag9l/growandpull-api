package com.growandpull.api.service;

import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.profile.Profile;
import com.growandpull.api.dto.profile.ProfileView;
import com.growandpull.api.dto.user.PasswordUpdateRequest;
import com.growandpull.api.dto.user.UserUpdateRequest;
import com.growandpull.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate);

    ProfileView updateUser(String userId, UserUpdateRequest userUpdateRequest, String userLogin) throws IOException;

    Profile getProfile(String userId, String currentUserEmail);

    User findUserByEmail(String email);
}

