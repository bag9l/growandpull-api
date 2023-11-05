package com.growandpull.api.service;

import com.growandpull.api.dto.PasswordUpdate;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdate passwordUpdate);

    User findUserByLogin(String login);
}
