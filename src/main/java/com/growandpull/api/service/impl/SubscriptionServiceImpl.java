package com.growandpull.api.service.impl;

import com.growandpull.api.dto.subscription.SubscriptionCard;
import com.growandpull.api.dto.subscription.SubscriptionDto;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.mapper.SubscriptionMapper;
import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.model.entity.SubscriptionType;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import com.growandpull.api.repository.SubscriptionRepository;
import com.growandpull.api.repository.SubscriptionTypeRepository;
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
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionMapper subscriptionMapper;


    @Override
    public SubscriptionDto findSubscriptionById(SubscriptionTypeIdentifier id) {
        SubscriptionType subscription = subscriptionTypeRepository.findSubscriptionTypeById(id).orElseThrow(() ->
                new EntityNotExistsException("Subscription:" + id + " not found"));
        return subscriptionMapper.subscriptionTypeToDto(subscription);
    }

    @Override
    public List<SubscriptionCard> findAllSubscriptions(){
        return subscriptionTypeRepository.findAll().stream()
                .map(subscriptionMapper::subscriptionTypeTOCard)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> findUnexpiredSubscriptionsForUserByEmail(String email) {
        return subscriptionRepository.findAllByOwnerEmailAndExpiresAtAfter(email, LocalDate.now());
    }

    @Transactional
    @Override
    public List<SubscriptionTypeIdentifier> getCurrentUserSubscriptions(String email) {
        return findUnexpiredSubscriptionsForUserByEmail(email).stream()
                .map((s) -> s.getType().getId())
                .toList();
    }
}
