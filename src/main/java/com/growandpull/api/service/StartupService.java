package com.growandpull.api.service;

import com.growandpull.api.dto.startup.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Optional;

public interface StartupService {
    StartupView getStartupById(String id);
    Page<StartupCard> findAllStartups(Integer pageNumber);
    Page<StartupCard> findAllStartups(Integer pageNumber, Optional<UserDetails> authenticatedUser);

    StartupView createStartup(StartupCreationRequest startup, String ownerLogin) throws IOException;

    StartupCreationData getCreationData();

    StartupView updateStartup(String startupId, StartupUpdateRequest startupUpdateRequest, String userLogin) throws IOException;
}
