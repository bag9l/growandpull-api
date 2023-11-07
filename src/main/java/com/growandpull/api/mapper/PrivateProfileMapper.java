package com.growandpull.api.mapper;

import com.growandpull.api.dto.PrivateProfile;
import com.growandpull.api.model.Profile;

public abstract class PrivateProfileMapper {
    public abstract PrivateProfile toDTO(Profile profile);

    public abstract Profile toModel(PrivateProfile privateProfile);
}
