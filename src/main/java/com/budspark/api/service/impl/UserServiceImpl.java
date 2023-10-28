package com.budspark.api.service.impl;

import com.budspark.api.dto.PasswordUpdate;
import com.budspark.api.dto.auth.AuthenticationRequest;
import com.budspark.api.dto.auth.AuthenticationResponse;
import com.budspark.api.exception.EntityNotExistsException;
import com.budspark.api.exception.PermissionException;
import com.budspark.api.model.User;
import com.budspark.api.repository.UserRepository;
import com.budspark.api.service.AuthenticationService;
import com.budspark.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Lazy
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException("User with id: " + id + " not found"));
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
                new EntityNotExistsException("User with login: " + login + " not found"));
    }
}
