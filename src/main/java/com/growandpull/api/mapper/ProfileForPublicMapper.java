package com.growandpull.api.mapper;

import com.growandpull.api.dto.ProfileForPublic;
import com.growandpull.api.dto.ProfileForView;
import com.growandpull.api.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProfileForPublicMapper {



        @Mapping(target = "fullName", source = "fullName")
        @Mapping(target = "gender", source = "gender")
        @Mapping(target = "email", source = "email")
        @Mapping(target = "phoneNumber", source = "phoneNumber")
        @Mapping(target = "birth", source = "birth")
        @Mapping(target = "description", source = "description")
        @Mapping(target = "avatar", source = "avatar")

        public abstract ProfileForPublic profileForPublicToProfileForPublicDTO(Profile profile);



}
