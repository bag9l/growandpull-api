package com.budspark.api.service;

import com.budspark.api.dto.NewStartup;
import com.budspark.api.dto.StartupView;

public interface StartupService {
    StartupView getStartupById(String id);

    StartupView createStartup(NewStartup newStartup, String ownerLogin);
}
