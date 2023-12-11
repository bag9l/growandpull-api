package com.growandpull.api.service.impl;

import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import com.growandpull.api.repository.SubscriptionRepository;
import com.growandpull.api.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    @Override
    public List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email){
        List<Subscription> subscriptions = subscriptionRepository.findNotExpiredSubscriptionsByUserEmail(email);
        return subscriptions.stream()
                .filter((s)-> !s.getIsExpired())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<SubscriptionTypeIdentifier> getCurrentUserSubscriptions(String email){
        return findUnexpiredSubscriptionsForUserByEmail(email).stream()
                .map((s) -> s.getType().getAppIdentifier())
                .toList();
    }
}
