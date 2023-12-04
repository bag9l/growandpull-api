package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Query("""
            SELECT s FROM Subscription s JOIN User u 
            WHERE u.email =:email AND s.expiresAt <= current_date()
            """)
    List<Subscription> findNotExpiredSubscriptionsByUserEmail(@Param("email") String email);
}
