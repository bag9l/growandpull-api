package com.growandpull.api.service.impl;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.startup.StartupCreationRequest;
import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.dto.startup.StartupView;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.mapper.FinanceMapper;
import com.growandpull.api.mapper.StartupMapper;
import com.growandpull.api.model.*;
import com.growandpull.api.repository.FinanceRepository;
import com.growandpull.api.repository.ImageRepository;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StartupServiceImplTest {

    @Mock
    private StartupRepository startupRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FinanceRepository financeRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private StartupMapper startupMapper;
    @Mock
    private FinanceMapper financeMapper;

    @InjectMocks
    private StartupServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnStartup_IfIdIsCorrect_WhenGetById() {
        // Arrange
        String id = "id";

        Startup startup = new Startup();

        StartupView expected = new StartupView(
                "title",
                null,
                null,
                "description",
                StartupStatus.IDEA,
                "category",
                null,
                LocalDateTime.now()
        );

        when(startupRepository.findById(id)).thenReturn(Optional.of(startup));
        when(startupMapper.startupToView(startup)).thenReturn(expected);

        // Act
        StartupView result = underTest.getStartupById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expected, result);
        verify(startupRepository).findById(id);
        verify(startupMapper).startupToView(startup);
    }

    @Test
    void shouldThrowEntityNotExistsException_IfIdIsIncorrect_WhenGetById() {
        // Arrange
        String id = "id";

        when(startupRepository.findById(id)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(EntityNotExistsException.class, () -> underTest.getStartupById(id));
    }

    @Test
    void shouldReturnPageOfStartups_WhenFindAllStartups() {
        // Arrange
        Startup startup = new Startup(
                "id",
                "title",
                null,
                "description",
                StartupStatus.IDEA,
                new Category("category"),
                null,
                AdStatus.ENABLED,
                null,
                LocalDateTime.now()
        );

        List<Startup> startups = new ArrayList<>();
        startups.add(startup);

        List<StartupCard> expectedStartupCards = new ArrayList<>();
        expectedStartupCards.add(startupMapper.startupToCard(startup));

        Pageable pageable = PageRequest.of(0, 20);
        Page<Startup> expectedPage = new PageImpl<>(startups, pageable, startups.size());

        when(startupRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<StartupCard> result = underTest.findAllStartups(0);

        // Assertions
        assertEquals(expectedStartupCards.size(), result.getTotalElements());
        assertEquals(expectedStartupCards.size(), result.getContent().size());
    }

    @Test
    void shouldCreateStartup_AndReturnStartupView() throws IOException {
        // Arrange
        MultipartFile image = new MockMultipartFile(
                "image", "image.png", "image/png", "some bytes".getBytes());
        FinanceDto financeDto = new FinanceDto(BigDecimal.TEN, Currency.EUR);
        String categoryId = "categoryId";
        StartupCreationRequest newStartup = new StartupCreationRequest(
                "Title",
                "Description",
                StartupStatus.IDEA,
                categoryId,
                financeDto,
                image);
        String ownerLogin = "testUserLogin";

        User user = new User(
                "test@example.com",
                "password",
                "Test",
                "User",
                Role.USER,
                null,
                null,
                null
        );

        Startup startup = new Startup();

        StartupView startupView = new StartupView(
                "Title",
                null,
                null,
                "Description",
                StartupStatus.IDEA,
                categoryId,
                financeDto,
                LocalDateTime.now()
        );

        when(startupMapper.newToStartup(newStartup)).thenReturn(startup);
        when(userRepository.findUserByEmail(ownerLogin)).thenReturn(Optional.of(user));
        when(startupRepository.save(any(Startup.class))).thenReturn(startup);
        when(startupMapper.startupToView(startup)).thenReturn(startupView);

        // Act
        StartupView result = underTest.createStartup(newStartup, ownerLogin);

        // Assertions
        assertEquals(result, startupView);

        verify(startupRepository).save(startup);
        verify(startupMapper).newToStartup(newStartup);
        verify(userRepository).findUserByEmail(ownerLogin);
    }

    @Test
    void shouldThrowEntityNotExistsException_IfIncorrectLogin_WhenCreateStartup() throws IOException {
        // Arrange
        String login = "email";

        when(userRepository.findById(login)).thenReturn(Optional.empty());
        when(startupMapper.newToStartup(any(StartupCreationRequest.class))).thenReturn(null);

        // Assertions
        assertThrows(EntityNotExistsException.class, () -> underTest.createStartup(null, login));
    }
}