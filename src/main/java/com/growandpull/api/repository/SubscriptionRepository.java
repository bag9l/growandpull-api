package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Query("""
            SELECT s FROM User u
            JOIN u.subscriptions s
            WHERE u.email =:email
            """)
    List<Subscription> findNotExpiredSubscriptionsByUserEmail(@Param("email") String email);
}
