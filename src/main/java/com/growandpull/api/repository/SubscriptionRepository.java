package com.growandpull.api.repository;

import com.growandpull.api.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    List<Subscription> findAllByOwnerEmailAndExpiresAtAfter(@Param("email") String email,
                                                            @Param("now") LocalDate dateNow);
}
