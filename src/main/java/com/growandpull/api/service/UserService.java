package com.growandpull.api.service;

import com.growandpull.api.dto.*;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.model.User;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public interface UserService {
    User findUserById(String id);

    AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate);

<<<<<<< HEAD
    User findUserByLogin(String login);

    ProfileView updateUser(String userId, UserUpdateRequest userUpdateRequest, String userLogin) throws IOException;
    Profile getProfile(String currentUserLogin, String userLogin);


=======
    User findUserByEmail(String login);
>>>>>>> master
}

