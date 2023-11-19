package com.growandpull.api.service;

import com.growandpull.api.dto.startup.*;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StartupService {
    StartupView getStartupById(String id);

    Page<StartupCard> findAllStartups(Integer pageNumber);

    StartupView createStartup(StartupCreationRequest startup, String ownerLogin) throws IOException;

    StartupCreateData getCreationData();

    StartupView updateStartup(String startupId, StartupUpdateRequest startupUpdateRequest, String userLogin) throws IOException;
}
