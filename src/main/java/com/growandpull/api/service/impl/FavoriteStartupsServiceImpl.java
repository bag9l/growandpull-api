package com.growandpull.api.service.impl;

import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.mapper.StartupMapper;
import com.growandpull.api.model.Startup;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.FavoriteStartupsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoriteStartupsServiceImpl implements FavoriteStartupsService {
    private final StartupRepository startupRepository;
    private final UserRepository userRepository;
    private final StartupMapper startupMapper;

    @Override
    public void addStartupToFavorite(String username, String startupId) {
        Startup startup = startupRepository.findById(startupId)
                .orElseThrow(() -> new EntityNotExistsException("Startup not found"));
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new EntityNotExistsException("User not found"));
        user.getFavoriteStartups().add(startup);
        userRepository.save(user);
    }

    @Override
    public Set<StartupCard> findFavoriteByUser(String login) {
        User user = userRepository.findUserByEmail(login)
                .orElseThrow(() -> new EntityNotExistsException("User not found"));
        return user.getFavoriteStartups().stream()
                .map(startupMapper::startupToCard)
                .collect(Collectors.toSet());
    }


    @Override
    public void deleteFromFavorite(String username, String startupId) {
        Startup startup = startupRepository.findById(startupId)
                .orElseThrow(() -> new EntityNotExistsException("Startup not found"));
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new EntityNotExistsException("User not found"));
        user.getFavoriteStartups().remove(startup);
        userRepository.save(user);
    }
}