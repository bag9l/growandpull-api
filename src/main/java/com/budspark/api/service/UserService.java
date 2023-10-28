package com.budspark.api.service;

import com.budspark.api.dto.PasswordUpdate;
import com.budspark.api.dto.auth.AuthenticationResponse;
import com.budspark.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdate passwordUpdate);

    User findUserByLogin(String login);
}
