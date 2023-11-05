package com.growandpull.api.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            SELECT t FROM Token t INNER JOIN User u
            ON t.user.id = u.id
            WHERE u.email = :email AND (t.isExpired = false OR t.isRevoked = false)
            """)
    List<Token> findAllValidTokensForUser(@Param("email") String email);

    Optional<Token> findByToken(String token);
}
