package com.growandpull.api.mapper;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.dto.startup.StartupCreationRequest;
import com.growandpull.api.dto.startup.StartupView;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.model.entity.Category;
import com.growandpull.api.model.entity.Finance;
import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.model.enums.AdStatus;
import com.growandpull.api.model.enums.Currency;
import com.growandpull.api.model.enums.Role;
import com.growandpull.api.model.enums.StartupStatus;
import com.growandpull.api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StartupMapperTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private FinanceMapper financeMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private FileMapper fileMapper;

    @InjectMocks
    private StartupMapperImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void shouldMapStartupToCard() {
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
    void shouldMapStartupToView() {
        // Arrange
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
        Category category = new Category("category");
        Finance finance = new Finance(BigDecimal.TEN, Currency.EUR);

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

    @Test
    void shouldMapNewToStartup() throws IOException {
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
        Finance finance = financeMapper.dtoToFinance(financeDto);
        Category category = new Category("name");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Startup startup = underTest.newToStartup(newStartup);

        // Assert
        assertEquals(startup.getTitle(), newStartup.getTitle());
        assertEquals(startup.getDescription(), newStartup.getDescription());
        assertEquals(startup.getCategory(), category);
        assertEquals(startup.getStatus(), newStartup.getStatus());
        assertEquals(startup.getFinance(), finance);
    }

}