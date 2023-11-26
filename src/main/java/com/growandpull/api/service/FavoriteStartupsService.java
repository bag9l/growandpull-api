package com.growandpull.api.service;

import com.growandpull.api.dto.startup.StartupCard;

import java.util.Set;

public interface FavoriteStartupsService {
    void addStartupToFavorite(String username, String startupId);

    Set<StartupCard> findFavoriteByUser(String login);

    void deleteFromFavorite(String userId, String startupId);
}
