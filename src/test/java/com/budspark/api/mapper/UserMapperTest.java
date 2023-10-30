package com.budspark.api.mapper;

import com.budspark.api.dto.UserCard;
import com.budspark.api.dto.auth.AuthenticatedUser;
import com.budspark.api.model.Role;
import com.budspark.api.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper underTest = Mappers.getMapper(UserMapper.class);

    @Test
    void testUserToAuthenticatedUser() {
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

        // Act
        AuthenticatedUser authenticatedUser = underTest.userToAuthenticatedUser(user);

        // Assert
        assertEquals(user.getLogin(), authenticatedUser.login());
        assertEquals(user.getFullName(), authenticatedUser.fullName());
        assertEquals(user.getEmail(), authenticatedUser.email());
        assertEquals(user.getRole().getValue(), authenticatedUser.role());
    }

    @Test
    void testUserToUserCard() {
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

        // Act
        UserCard userCard = underTest.userToCard(user);

        // Assert
        assertEquals(user.getId(), userCard.id());
        assertEquals(user.getFullName(), userCard.fullName());
    }
}