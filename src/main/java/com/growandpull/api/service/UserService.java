package com.growandpull.api.service;

import com.growandpull.api.dto.PasswordUpdateRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate);

    User findUserByLogin(String login);

    User updateUser(String userId, User updatedUser);


}
