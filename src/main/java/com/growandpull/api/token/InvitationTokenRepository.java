package com.growandpull.api.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface InvitationTokenRepository extends JpaRepository<InvitationToken, Integer> {
    Optional<InvitationToken> findByToken(String token);

    @Transactional
    void deleteByCreatedAtBefore(LocalDate expirationDate);

}
