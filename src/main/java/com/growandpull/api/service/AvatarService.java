package com.growandpull.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(MultipartFile file, String userId) throws IOException;

    byte[] getUserAvatar(String userId);
}
