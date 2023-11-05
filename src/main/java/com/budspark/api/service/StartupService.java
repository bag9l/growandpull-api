package com.budspark.api.service;

import com.budspark.api.dto.StartupCard;
import com.budspark.api.dto.StartupView;
import org.springframework.data.domain.Page;

public interface StartupService {
    StartupView getStartupById(String id);

    Page<StartupCard> findAllStartups(Integer pageNumber);
}
