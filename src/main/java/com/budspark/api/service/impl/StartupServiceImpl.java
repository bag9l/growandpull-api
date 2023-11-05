package com.budspark.api.service.impl;

import com.budspark.api.dto.NewStartup;
import com.budspark.api.dto.StartupView;
import com.budspark.api.exception.EntityNotExistsException;
import com.budspark.api.mapper.StartupMapper;
import com.budspark.api.model.Startup;
import com.budspark.api.model.User;
import com.budspark.api.repository.StartupRepository;
import com.budspark.api.repository.UserRepository;
import com.budspark.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public StartupView createStartup(NewStartup newStartup, String ownerLogin) {
        User owner = userRepository.findUserByLogin(ownerLogin).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_LOGIN_NOT_FOUND, ownerLogin)));

        return null;
    }
}
