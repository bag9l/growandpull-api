package com.growandpull.api.service;

import com.growandpull.api.model.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email);
}
