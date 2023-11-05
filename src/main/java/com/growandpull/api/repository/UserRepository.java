package com.growandpull.api.repository;

import com.growandpull.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(String id);
}
