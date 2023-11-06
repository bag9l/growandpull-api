package com.growandpull.api.service;

import com.growandpull.api.dto.NewStartup;
import com.growandpull.api.dto.StartupCard;
import com.growandpull.api.dto.StartupView;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StartupService {
    StartupView getStartupById(String id);

    Page<StartupCard> findAllStartups(Integer pageNumber);

    StartupView createStartup(NewStartup startup, String ownerLogin) throws IOException;
}
