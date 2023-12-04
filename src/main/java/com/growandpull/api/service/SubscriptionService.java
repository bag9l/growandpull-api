package com.growandpull.api.service;

import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email);

    @Transactional
    List<SubscriptionTypeIdentifier> getCurrentUserSubscriptions(String email);
}
