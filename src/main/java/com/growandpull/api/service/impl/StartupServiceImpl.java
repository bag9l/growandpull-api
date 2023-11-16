package com.growandpull.api.service.impl;

import com.growandpull.api.dto.*;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.exception.PermissionException;
import com.growandpull.api.mapper.FinanceMapper;
import com.growandpull.api.mapper.ImageMapper;
import com.growandpull.api.mapper.StartupMapper;
import com.growandpull.api.model.*;
import com.growandpull.api.repository.*;
import com.growandpull.api.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StartupServiceImpl implements StartupService {

    private static final String STARTUP_NOT_FOUND = "Startup with id:%s not found";
    private static final String USER_WITH_LOGIN_NOT_FOUND = "User with email:%s not found";

    private final StartupRepository startupRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final FinanceRepository financeRepository;
    private final StartupMapper startupMapper;
    private final FinanceMapper financeMapper;
    private final ImageMapper imageMapper;


    @Override
    public StartupView getStartupById(String id) {
        return startupMapper.startupToView(findStartupById(id));
    }

    @Override
    public Page<StartupCard> findAllStartups(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20);

        Page<Startup> page = startupRepository.findAll(pageable);

        return page.map(startupMapper::startupToCard);
    }

    @Override
    public StartupView createStartup(StartupCreationRequest newStartup, String ownerLogin) throws IOException {
        Startup startup = startupMapper.newToStartup(newStartup);
        User user = findUserByLogin(ownerLogin);

        startup.setOwner(user);
        startup.setAdStatus(AdStatus.ENABLED);
        startup.setCreatedAt(LocalDateTime.now());

        startup = startupRepository.save(startup);
        return startupMapper.startupToView(startup);
    }

    @Override
    public StartupCreateData getCreationData() {
        List<StartupStatus> statuses = List.of(StartupStatus.values());
        List<Category> categories = categoryRepository.findAll();
        return new StartupCreateData(statuses, categories);
    }

    //    TODO: try loading
    @Override
    public StartupView updateStartup(String startupId, StartupUpdateRequest startupUpdateRequest, String userLogin) throws IOException {
        Startup startup = findStartupById(startupId);
        User user = findUserByLogin(userLogin);

        if (!user.getUsername().equals(startup.getOwner().getUsername())) {
            throw new PermissionException("Only the owner can edit the startup");
        }
        copyUpdateFieldsToStartup(startupUpdateRequest, startup);

        startup = startupRepository.save(startup);

        return startupMapper.startupToView(startup);
    }

    private void copyUpdateFieldsToStartup(StartupUpdateRequest startupUpdateRequest, Startup startup) throws IOException {
        Category category = categoryRepository.findById(startupUpdateRequest.getCategoryId()).orElseThrow(() ->
                new EntityNotExistsException(String.format("Category with id:%s not found", startupUpdateRequest.getCategoryId())));
        Image image = (startupUpdateRequest.getImage() != null) ?
                imageMapper.multiPartFileToImage(startupUpdateRequest.getImage()) : null;
        Finance finance = financeMapper.dtoToFinance(startupUpdateRequest.getFinance());

        if (image != null) {
            image = imageRepository.save(image);
        }
        finance = financeRepository.save(finance);

        startup.setTitle(startupUpdateRequest.getTitle());
        startup.setDescription(startupUpdateRequest.getDescription());
        startup.setImage(image);
        startup.setStatus(startupUpdateRequest.getStatus());
        startup.setCategory(category);
        startup.setFinance(finance);
    }

    private Startup findStartupById(String id) {
        return startupRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException(String.format(STARTUP_NOT_FOUND, id)));
    }

    private User findUserByLogin(String ownerLogin) {
        return userRepository.findUserByEmail(ownerLogin).orElseThrow(() ->
                new EntityNotExistsException(String.format(USER_WITH_LOGIN_NOT_FOUND, ownerLogin)));
    }
}
