package com.growandpull.api.mapper;

import com.growandpull.api.dto.profile.PrivateProfile;
import com.growandpull.api.dto.profile.ProfileView;
import com.growandpull.api.dto.profile.PublicProfile;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.dto.auth.AuthenticatedUser;
import com.growandpull.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Mapping(target = "role", source = "role.value")
    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract UserCard userToCard(User user);


    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract PrivateProfile userToPrivateProfile(User user);

    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract PublicProfile userToPublicProfile(User user);

    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract ProfileView userToProfileView(User user);



}
