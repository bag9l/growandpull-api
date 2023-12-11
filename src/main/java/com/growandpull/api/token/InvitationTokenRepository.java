package com.growandpull.api.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationTokenRepository extends JpaRepository<InvitationToken, Integer> {
    Optional<InvitationToken> findByToken(String token);

}
