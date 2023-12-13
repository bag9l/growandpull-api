package com.growandpull.api.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByToken(String confirmationToken);
}
