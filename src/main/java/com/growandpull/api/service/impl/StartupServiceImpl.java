package com.growandpull.api.service.impl;

import com.growandpull.api.dto.NewStartup;
import com.growandpull.api.dto.StartupCard;
import com.growandpull.api.dto.StartupView;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.mapper.StartupMapper;
import com.growandpull.api.model.AdStatus;
import com.growandpull.api.model.Startup;
import com.growandpull.api.model.User;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class StartupServiceImpl implements StartupService {

    private static final String STARTUP_NOT_FOUND = "Startup with id:%s not found";
    private static final String USER_WITH_LOGIN_NOT_FOUND = "User with login:%s not found";

    private final StartupRepository startupRepository;
    private final UserRepository userRepository;
    private final StartupMapper startupMapper;


    @Override
    public StartupView getStartupById(String id) {
        Startup startup = startupRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(STARTUP_NOT_FOUND, id)));

        return startupMapper.startupToView(startup);
    }

    @Override
    public Page<StartupCard> findAllStartups(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20);

        Page<Startup> page = startupRepository.findAll(pageable);

        return page.map(startupMapper::startupToCard);
    }

    @Override
    public StartupView createStartup(NewStartup newStartup, String ownerLogin) throws IOException {
        Startup startup = startupMapper.newToStartup(newStartup);
        User user = userRepository.findUserByLogin(ownerLogin).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_LOGIN_NOT_FOUND, ownerLogin)));

        startup.setOwner(user);
        startup.setAdStatus(AdStatus.ENABLED);
        startup.setCreatedAt(LocalDateTime.now());

        startup = startupRepository.save(startup);
        return startupMapper.startupToView(startup);
    }
}
