package com.budspark.api.service.impl;

import com.budspark.api.dto.StartupView;
import com.budspark.api.mapper.StartupMapper;
import com.budspark.api.model.*;
import com.budspark.api.repository.StartupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StartupServiceImplTest {

    @Mock
    private StartupRepository startupRepository;

    @Mock
    private StartupMapper startupMapper;

    @InjectMocks
    private StartupServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetStartupById() {
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
}