package com.growandpull.api.mapper;

import com.growandpull.api.dto.UserCard;
import com.growandpull.api.dto.auth.AuthenticatedUser;
import com.growandpull.api.model.Role;
import com.growandpull.api.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper underTest = Mappers.getMapper(UserMapper.class);

    @Test
    void testUserToAuthenticatedUser() {
        // Arrange
        User user = new User(
                "test@example.com",
                "password",
                "Test User",
                Role.USER,
                null,
                null,
                null
        );

        // Act
        AuthenticatedUser authenticatedUser = underTest.userToAuthenticatedUser(user);

        // Assert
        assertEquals(user.getUsername(), authenticatedUser.email());
        assertEquals(user.getFirstName(), authenticatedUser.fullName());
        assertEquals(user.getEmail(), authenticatedUser.email());
        assertEquals(user.getRole().getValue(), authenticatedUser.role());
    }

    @Test
    void testUserToUserCard() {
        // Arrange
        User user = new User(
                "password",
                "Test User",
                "test@example.com",
                Role.USER,
                null,
                null,
                null
        );

        // Act
        UserCard userCard = underTest.userToCard(user);

        // Assert
        assertEquals(user.getId(), userCard.id());
        assertEquals(user.getFirstName(), userCard.fullName());
    }
}