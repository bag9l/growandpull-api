package com.growandpull.api.service;

import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.user.*;
import com.growandpull.api.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public interface UserService {
    User findUserById(String id);

    UserUpdateData getUserUpdateData();

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate);

    PrivateProfile updateUser(String userId, UserUpdateRequest userUpdateRequest, String userLogin) throws IOException;

    Profile getProfile(String userId, String currentUserEmail);

    User findUserByEmail(String email);
}

