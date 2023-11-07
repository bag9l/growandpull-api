package com.growandpull.api.mapper;

import com.growandpull.api.model.Image;
import com.growandpull.api.util.ImageUtil;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ImageMapper {

    public Image multiPartFileToImage(MultipartFile multipartFile) throws IOException {
        return new Image(multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                ImageUtil.compressImage(multipartFile.getBytes()));
    }
}
