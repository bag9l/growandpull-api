package com.growandpull.api.service;

import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.profile.Profile;
import com.growandpull.api.dto.profile.ProfileView;
import com.growandpull.api.dto.user.PasswordUpdateRequest;
import com.growandpull.api.dto.user.UserUpdateRequest;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate);

    ProfileView updateUser(String userId, UserUpdateRequest userUpdateRequest, String userLogin) throws IOException;

    Profile getProfile(String userId, String currentUserEmail);

    User findUserByEmail(String email);
}

