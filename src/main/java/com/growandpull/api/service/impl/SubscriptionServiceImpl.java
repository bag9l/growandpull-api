package com.growandpull.api.service.impl;

import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.repository.SubscriptionRepository;
import com.growandpull.api.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    @Override
    public List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email){
        return subscriptionRepository.findNotExpiredSubscriptionsByUserEmail(email);
    }
}
