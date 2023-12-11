package com.growandpull.api.config;

import com.growandpull.api.model.entity.Finance;
import com.growandpull.api.model.entity.Subscription;
import com.growandpull.api.model.entity.SubscriptionType;
import com.growandpull.api.model.enums.Currency;
import com.growandpull.api.model.enums.SubscriptionTypeIdentifier;
import com.growandpull.api.repository.FinanceRepository;
import com.growandpull.api.repository.SubscriptionRepository;
import com.growandpull.api.repository.SubscriptionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Period;

@RequiredArgsConstructor
@Configuration
public class DbInitializer {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final FinanceRepository financeRepository;

//    @Bean
//    public void addStartupSubscription() {
//        Finance finance = new Finance(
//                "2c9180828bd49883018bd45c16690032",
//                BigDecimal.valueOf(10.0),
//                Currency.USD
//        );
//        finance = financeRepository.save(finance);
//
//
//        SubscriptionType subscriptionType = new SubscriptionType(
//                "2c9180828bd49883018bd45c36690032",
//                SubscriptionTypeIdentifier.STARTUP_PACK,
//                "startup",
//                finance,
//                Period.ofMonths(1)
//        );
//        subscriptionType = subscriptionTypeRepository.save(subscriptionType);
//
//
//        Subscription subscription = new Subscription(
//                "2c918082v3d49883018bd45c36690032",
//                subscriptionType
//        );
//        subscription = subscriptionRepository.save(subscription);
//    }
}
