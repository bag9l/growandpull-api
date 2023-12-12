package com.growandpull.api.repository;

import com.growandpull.api.model.entity.SubscriptionType;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, SubscriptionTypeIdentifier> {

    Optional<SubscriptionType> findSubscriptionTypeByAppIdentifier(SubscriptionTypeIdentifier id);
}
