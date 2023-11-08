package com.growandpull.api.service;

import com.growandpull.api.dto.ProfileForView;
import com.growandpull.api.model.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileForViewService {
    void saveUserProfileFromUser(String userId);
    public void updateProfile(String userId, ProfileForView updatedProfile, MultipartFile avatarImage);


}
