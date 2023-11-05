package com.growandpull.api.service;

import com.growandpull.api.dto.StartupView;

public interface StartupService {
    StartupView getStartupById(String id);
}
