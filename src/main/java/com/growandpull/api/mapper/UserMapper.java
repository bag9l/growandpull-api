package com.growandpull.api.mapper;

import com.growandpull.api.dto.user.PrivateProfile;
import com.growandpull.api.dto.user.PublicProfile;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.dto.auth.AuthenticatedUser;
import com.growandpull.api.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {EducationMapper.class, CityMapper.class})
public abstract class UserMapper {

    protected EducationMapper educationMapper;
    protected CityMapper cityMapper;

    @Mapping(target = "role", source = "role.value")
    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract UserCard userToCard(User user);


    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    @Mapping(target = "education", expression = "java(educationMapper.educationToDto(user.getEducation()))")
    @Mapping(target = "city", expression = "java(cityMapper.cityToDto(user.getCity()))")
    public abstract PrivateProfile userToPrivateProfile(User user);

    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    @Mapping(target = "education", expression = "java(educationMapper.educationToDto(user.getEducation()))")
    @Mapping(target = "city", expression = "java(cityMapper.cityToDto(user.getCity()))")
    public abstract PublicProfile userToPublicProfile(User user);

    @Autowired
    public void setEducationMapper(EducationMapper educationMapper) {
        this.educationMapper = educationMapper;
    }

    @Autowired
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }
}
