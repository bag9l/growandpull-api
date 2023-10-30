package com.budspark.api.service;

import com.budspark.api.dto.StartupView;

public interface StartupService {
    StartupView getStartupById(String id);
}
