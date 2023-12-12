package com.growandpull.api.service;

import com.growandpull.api.dto.subscription.SubscriptionCard;
import com.growandpull.api.dto.subscription.SubscriptionDto;
import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto findSubscriptionByType(SubscriptionTypeIdentifier type);

    List<SubscriptionCard> findAllSubscriptions();

    List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email);

    @Transactional
    List<SubscriptionTypeIdentifier> getCurrentUserSubscriptions(String email);
}
