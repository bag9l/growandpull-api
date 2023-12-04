package com.growandpull.api.repository;

import com.growandpull.api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(String id);

    @Query("""
            SELECT CASE WHEN count(s) > 0 THEN true ELSE false END 
            FROM Startup s JOIN User u
            WHERE u.email=:email AND s.createdAt>:threeMonthsAgo
            """)
    Boolean haveUserBeenCreatedStartupAfterTime(@Param("email") String email,
                                                @Param("threeMonthsAgo") LocalDateTime threeMonthsAgo);
}
