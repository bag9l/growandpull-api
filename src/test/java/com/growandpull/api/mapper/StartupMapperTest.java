package com.growandpull.api.mapper;

import com.growandpull.api.dto.FinanceDto;
import com.growandpull.api.dto.StartupCard;
import com.growandpull.api.dto.StartupView;
import com.growandpull.api.dto.UserCard;
import com.growandpull.api.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartupMapperTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private FinanceMapper financeMapper;

    @InjectMocks
    private StartupMapperImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testStartupToCard(){
        // Arrange
        Category category = new Category("category");

        Startup startup = new Startup(
                "id",
                "title",
                null,
                "description",
                StartupStatus.IDEA,
                category,
                null,
                AdStatus.ENABLED,
                null,
                LocalDateTime.now()
        );

        // Act
        StartupCard card = underTest.startupToCard(startup);

        // Assert
        assertEquals(startup.getId(), card.id());
        assertEquals(startup.getTitle(), card.title());
        assertEquals(startup.getCategory().getName(), card.category());
        assertEquals(startup.getDescription(), card.description());
    }

    @Test
    void testStartupToView(){
        // Arrange
        User user = new User(
                "testUser",
                "password",
                "Test User",
                "test@example.com",
                Role.USER,
                null,
                null,
                null
        );
        Category category = new Category("category");
        Finance finance = new Finance(BigDecimal.TEN, BigDecimal.ONE, Currency.EUR);

        Startup startup = new Startup(
                "id",
                "title",
                user,
                "description",
                StartupStatus.IDEA,
                category,
                finance,
                AdStatus.ENABLED,
                null,
                LocalDateTime.now()
        );
        UserCard userCard = userMapper.userToCard(user);
        FinanceDto financeDto = financeMapper.financeToDto(finance);

        // Act
        StartupView view = underTest.startupToView(startup);

        // Assert
        assertEquals(startup.getTitle(), view.title());
        assertEquals(userCard, view.owner());
        assertEquals(startup.getCategory().getName(), view.category());
        assertEquals(startup.getDescription(), view.description());
        assertEquals(startup.getStatus(), view.status());
        assertEquals(financeDto, view.finance());
    }

}