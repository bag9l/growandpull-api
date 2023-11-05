package com.growandpull.api.service.impl;

import com.growandpull.api.dto.PasswordUpdate;
import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_WITH_ID_NOT_EXISTS = "User with id:%s not found";
    private static final String USER_WITH_LOGIN_NOT_EXISTS = "User with login:%s not found";

    private final UserRepository userRepository;
    @Lazy
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_ID_NOT_EXISTS, id)));
    }

    @Transactional
    @Override
    public AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdate passwordUpdate) {
        User user = findUserByLogin(userDetails.getUsername());

        if (!passwordEncoder.matches(passwordUpdate.oldPassword(), user.getPassword())) {
            throw new PermissionException("Password is not valid!");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdate.newPassword()));

        userRepository.save(user);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(user.getLogin(), passwordUpdate.newPassword());
        return authenticationService.authenticate(authenticationRequest);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_LOGIN_NOT_EXISTS, login)));
    }
}
