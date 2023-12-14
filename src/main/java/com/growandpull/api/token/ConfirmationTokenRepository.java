package com.growandpull.api.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByToken(String confirmationToken);

    @Transactional
    void deleteByCreatedAtBefore(LocalDate expirationDate);
}
