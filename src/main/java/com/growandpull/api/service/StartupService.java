package com.growandpull.api.service;

import com.growandpull.api.dto.StartupCreationRequest;
import com.growandpull.api.dto.StartupCard;
import com.growandpull.api.dto.StartupUpdateRequest;
import com.growandpull.api.dto.StartupView;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StartupService {
    StartupView getStartupById(String id);

    Page<StartupCard> findAllStartups(Integer pageNumber);

    StartupView createStartup(StartupCreationRequest startup, String ownerLogin) throws IOException;

    StartupView updateStartup(String startupId, StartupUpdateRequest startupUpdateRequest, String userLogin) throws IOException;
}
