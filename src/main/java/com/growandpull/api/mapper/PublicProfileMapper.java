package com.growandpull.api.mapper;

import com.growandpull.api.dto.PublicProfile;
import com.growandpull.api.model.Profile;
import org.mapstruct.Mapper;

@Mapper
public abstract class PublicProfileMapper {
    public abstract PublicProfile toDTO(Profile profile);

    public abstract Profile toModel(PublicProfile publicProfile);

}
