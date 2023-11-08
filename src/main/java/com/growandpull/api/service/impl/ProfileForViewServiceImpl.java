package com.growandpull.api.service.impl;


import com.growandpull.api.dto.ProfileForView;
import com.growandpull.api.mapper.ProfileForViewMapper;
import com.growandpull.api.model.Profile;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.ProfileRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.AvatarService;
import com.growandpull.api.service.ProfileForViewService;
import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;


@Service

public class ProfileForViewServiceImpl implements ProfileForViewService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileForViewMapper profileForViewMapper;
    @Autowired
    AvatarService avatarService;


    @Override
    public void saveUserProfileFromUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + userId));

        Profile profile = new Profile();
        profile.setFullName(user.getFullName());
        profile.setLogin(user.getLogin());
        profile.setAvatar(user.getAvatar());
        profile.setEmail(user.getEmail());
        profile.setPassword(user.getPassword());
        profileRepository.save(profile);
    }


    @Override
    public void updateProfile(String userId, ProfileForView updatedProfile, MultipartFile avatarImage) {
        Profile profile = profileRepository.findByUserId(userId);

        if (profile == null) {
            throw new EntityNotFoundException("Profile not found for user with ID: " + userId);
        }

        profile.setFullName(updatedProfile.getFullName());
        profile.setGender(updatedProfile.getGender());
        profile.setEmail(updatedProfile.getEmail());
        profile.setPhoneNumber(updatedProfile.getPhoneNumber());
        profile.setBirth(updatedProfile.getBirth());
        profile.setDescription(updatedProfile.getDescription());
        profile.setPassword(updatedProfile.getPassword());

        if (avatarImage != null) {
            try {
                avatarService.uploadAvatar(avatarImage, userId);
            } catch (IOException | java.io.IOException ignored) {
            }
        }


        profileRepository.save(profile);
    }
}
