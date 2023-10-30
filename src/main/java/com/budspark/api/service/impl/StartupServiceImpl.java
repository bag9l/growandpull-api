package com.budspark.api.service.impl;

import com.budspark.api.dto.StartupView;
import com.budspark.api.exception.EntityNotExistsException;
import com.budspark.api.mapper.StartupMapper;
import com.budspark.api.model.Startup;
import com.budspark.api.repository.StartupRepository;
import com.budspark.api.service.StartupService;
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
