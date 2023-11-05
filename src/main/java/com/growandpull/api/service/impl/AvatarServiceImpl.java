package com.growandpull.api.service.impl;

import com.growandpull.api.model.Avatar;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.AvatarRepository;
import com.growandpull.api.service.AvatarService;
import com.growandpull.api.service.UserService;
import com.growandpull.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final UserService userService;


    @Override
    public void uploadAvatar(MultipartFile file, String userId) throws IOException {

        User user = userService.findUserById(userId);

        Avatar avatar = new Avatar(file.getOriginalFilename(),
                file.getContentType(),
                ImageUtil.compressImage(file.getBytes()),
                user);

        avatarRepository.save(avatar);
    }

    @Override
    public byte[] getUserAvatar(String userId) {
        User user = userService.findUserById(userId);

        return ImageUtil.decompressImage(user.getAvatar().getImageData());
    }
}
