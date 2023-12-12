package com.growandpull.api.service.impl;

import com.growandpull.api.dto.auth.AuthenticationRequest;
import com.growandpull.api.dto.auth.AuthenticationResponse;
import com.growandpull.api.dto.location.CityDto;
import com.growandpull.api.dto.user.*;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.mapper.CityMapper;
import com.growandpull.api.mapper.EducationMapper;
import com.growandpull.api.mapper.FileMapper;
import com.growandpull.api.mapper.UserMapper;
import com.growandpull.api.model.entity.Avatar;
import com.growandpull.api.model.entity.City;
import com.growandpull.api.model.entity.Education;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.model.enums.Degree;
import com.growandpull.api.repository.AvatarRepository;
import com.growandpull.api.repository.CityRepository;
import com.growandpull.api.repository.EducationRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AuthenticationService;
import com.growandpull.api.service.SubscriptionService;
import com.growandpull.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_WITH_ID_NOT_EXISTS = "User with id:%s not found";
    private static final String USER_WITH_LOGIN_NOT_EXISTS = "User with email:%s not found";

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;
    private final CityRepository cityRepository;
    private final EducationRepository educationRepository;

    @Lazy
    private final AuthenticationService authenticationService;
    @Lazy
    private final SubscriptionService subscriptionService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final FileMapper fileMapper;
    private final EducationMapper educationMapper;
    private final CityMapper cityMapper;


    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_ID_NOT_EXISTS, id)));
    }

    @Override
    public UserUpdateData getUserUpdateData() {
        List<Degree> degrees = Arrays.asList(Degree.values());
        List<CityDto> cities = cityRepository.findAll().stream()
                .map(cityMapper::cityToDto)
                .toList();
        return new UserUpdateData(degrees, cities);
    }


    @Transactional
    @Override
    public AuthenticationResponse updatePassword(UserDetails userDetails, PasswordUpdateRequest passwordUpdate) {
        User user = findUserByEmail(userDetails.getUsername());

        if (!passwordEncoder.matches(passwordUpdate.oldPassword(), user.getPassword())) {
            throw new PermissionException("Password is not valid!");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdate.newPassword()));

        user = userRepository.save(user);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(user.getUsername(), passwordUpdate.newPassword());
        return authenticationService.authenticate(authenticationRequest);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_LOGIN_NOT_EXISTS, email)));
    }

    @Override
    public PrivateProfile updateUser(String userId, UserUpdateRequest userUpdateRequest, String email) throws IOException {
        User user = findUserByEmail(email);
        if (!user.getId().equals(userId)) {
            throw new PermissionException("Only the owner can edit the profile");

        }

        copyUpdateFieldsToUser(userUpdateRequest, user);

        user = userRepository.save(user);

        return userMapper.userToPrivateProfile(user);
    }

    @Override
    public Profile getProfile(String userId, String currentUserEmail) {
        User user = findUserById(userId);

        if (Objects.equals(currentUserEmail, user.getEmail())) {
            return userMapper.userToPrivateProfile(user);
        } else {
            return userMapper.userToPublicProfile(user);
        }
    }

    private void copyUpdateFieldsToUser(UserUpdateRequest updateRequest, User user) throws IOException {
        Avatar avatar = (updateRequest.getAvatar() != null) ?
                fileMapper.multiPartFileToAvatar(updateRequest.getAvatar()) : null;
        City city = cityRepository.findById(updateRequest.getCityId()).orElseThrow(() ->
                new EntityNotExistsException("City with id:" + updateRequest.getCityId() + " not found"));
        Education education = new Education();

        if (user.getEducation() != null) {
            education = educationRepository.findById(user.getEducation().getId()).get();
        }
        educationMapper.dtoToEducation(updateRequest.getEducation(), education);

        if (avatar != null) {
            avatar = avatarRepository.save(avatar);
        }

        user.setEmail(updateRequest.getEmail());
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setBirthday(updateRequest.getBirthday());
        user.setAbout(updateRequest.getAbout());
        user.setAvatar(avatar);
        user.setPhoneNumber(updateRequest.getPhoneNumber());
        user.setCity(city);
        user.setEducation(education);
        user.setExperience(updateRequest.getExperience());
    }
}
