package com.growandpull.api.repository;

import com.growandpull.api.model.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, String> {
}
