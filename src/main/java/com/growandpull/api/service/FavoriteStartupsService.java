package com.growandpull.api.service;

import com.growandpull.api.dto.startup.StartupCard;

import java.util.Set;

public interface FavoriteStartupsService {
    void addStartupToFavorite(String email, String startupId);

    Set<StartupCard> findFavoriteStartupsByUsername(String email);

    void deleteFromFavorite(String email, String startupId);
}
