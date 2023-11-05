package com.growandpull.api.service.impl;

import com.growandpull.api.dto.StartupView;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.mapper.StartupMapper;
import com.growandpull.api.model.Startup;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StartupServiceImpl implements StartupService {

    private static final String STARTUP_NOT_EXISTS = "Startup with id:%s not found";

    private final StartupRepository startupRepository;
    private final StartupMapper startupMapper;


    @Override
    public StartupView getStartupById(String id) {
        Startup startup = startupRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(STARTUP_NOT_EXISTS, id)));

        return startupMapper.startupToView(startup);
    }
}
