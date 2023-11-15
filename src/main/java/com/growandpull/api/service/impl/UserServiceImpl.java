package com.growandpull.api.service.impl;

import com.growandpull.api.dto.*;
import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.mapper.ImageMapper;
import com.growandpull.api.mapper.UserMapper;
import com.growandpull.api.model.Image;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.AvatarRepository;
import com.growandpull.api.repository.ImageRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_WITH_ID_NOT_EXISTS = "User with id:%s not found";
    private static final String USER_WITH_LOGIN_NOT_EXISTS = "User with login:%s not found";

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;

    @Lazy
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;


    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_ID_NOT_EXISTS, id)));
    }

    @Transactional
    @Override
    public AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate) {
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

    @Override
    public ProfileView updateUser(String userId, UserUpdateRequest userUpdateRequest, String userLogin) throws IOException {
        User user = findUserByLogin(userLogin);
        if (!user.getId().equals(userId)) {
            throw new PermissionException("Only the owner can edit the profile");

        }

        copyUpdateFieldsToUser(userUpdateRequest, user);

        user = userRepository.save(user);

        return userMapper.userToProfileView(user);


    }


    private void copyUpdateFieldsToUser(UserUpdateRequest userUpdateRequest, User user) throws IOException {
        Image image = (userUpdateRequest.getAvatar() != null) ?
                imageMapper.multiPartFileToImage(userUpdateRequest.getAvatar()) : null;

        if (image != null) {
            image = imageRepository.save(image);
        }

        user.setLogin(userUpdateRequest.getLogin());
        user.setAboutUser(userUpdateRequest.getAboutUser());
        user.setEmail(userUpdateRequest.getEmail());
        user.setFullName(userUpdateRequest.getFullName());
    }

    @Override
    public Profile getProfile(String currentUserLogin, String userLogin) {//currentUserLogin - логін залогованого корисувача, userLogin- логін користувача  сторінки

        if (Objects.equals(currentUserLogin, userLogin)) {
            User user = findUserByLogin(currentUserLogin);
            return new PrivateProfile(
                    user.getFullName(),
                    user.getBirth(),
                    user.getAboutUser(),
                    user.getAvatar().getImageData()
            );

        } else {
            User user = findUserByLogin(userLogin);
            return new PublicProfile(
                    user.getFullName(),
                    user.getBirth(),
                    user.getAboutUser(),
                    user.getAvatar().getImageData()
            );

        }
    }
}