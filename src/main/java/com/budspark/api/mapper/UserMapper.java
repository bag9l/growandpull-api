package com.budspark.api.mapper;

import com.budspark.api.dto.UserCard;
import com.budspark.api.dto.auth.AuthenticatedUser;
import com.budspark.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Mapping(target = "role", source = "role.value")
    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.budspark.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract AuthenticatedUser userToAuthenticatedUser(User user);

    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "avatar", expression = "java((user.getAvatar() != null) ? " +
            "com.budspark.api.util.ImageUtil.decompressImage(user.getAvatar().getImageData()) : null)")
    public abstract UserCard userToCard(User user);
}
