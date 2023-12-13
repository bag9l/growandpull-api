package com.growandpull.api.config;

import com.growandpull.api.model.entity.*;
import com.growandpull.api.model.enums.*;
import com.growandpull.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DbInitializer {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final FinanceRepository financeRepository;

    private final StartupRepository startupRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


//    @Bean
//    public void generateStartups() {
//        User user = new User(
//                "frontend@gmail.com",
//                "$2a$10$4aTcbOJn.LwWmu3wv6AxaOyD4QFtrimJrXzpuLyXI2fMNCT/Ee.FG",
//                "Frontend",
//                "Developer",
//                Role.USER,
//                null,
//                null,
//                null
//        );
//        Category category = new Category("Art");
//        Finance finance = new Finance(BigDecimal.valueOf(14300), Currency.EUR);
//        StartupDetails details = new StartupDetails(
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "'Content here, content here', making it look like readable English. Many desktop " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "'Content here, content here', making it look like readable English. Many desktop " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "'Content here, content here', making it look like readable English. Many desktop " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                        "sometimes on purpose (injected humour and the like).",
//                "It is a long established fact that a reader will be distracted by the readable " +
//                        "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                        "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                        "'Content here, content here', making it look like readable English. Many desktop " +
//                        "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                        "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                        "infancy. Various versions have evolved over the years, sometimes by accident"
//        );
//
//        user = userRepository.save(user);
//        category = categoryRepository.save(category);
//        finance = financeRepository.save(finance);
//
//        List<Startup> startups = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            startups.add(new Startup(
//                    "Where does it come from" + i,
//                    "It is a long established fact that a reader will be distracted by the readable " +
//                            "content of a page when looking at its layout. The point of using Lorem Ipsum is " +
//                            "that it has a more-or-less normal distribution of letters, as opposed to using " +
//                            "'Content here, content here', making it look like readable English. Many desktop " +
//                            "publishing packages and web page editors now use Lorem Ipsum as their default model " +
//                            "text, and a search for 'lorem ipsum' will uncover many web sites still in their " +
//                            "infancy. Various versions have evolved over the years, sometimes by accident, " +
//                            "sometimes on purpose (injected humour and the like).",
//                    StartupStatus.IDEA,
//                    category,
//                    details,
//                    user,
//                    finance,
//                    AdStatus.ENABLED,
//                    null
//            ));
//        }
//        startupRepository.saveAll(startups);
//    }

//    @Bean
//    public void generateUser() {
//        User user = new User(
//                "test@example.com",
//                "$2a$10$4aTcbOJn.LwWmu3wv6AxaOyD4QFtrimJrXzpuLyXI2fMNCT/Ee.FG",
//                "Test",
//                "User",
//                Role.USER,
//                null,
//                null,
//                null
//        );
//
//        userRepository.save(user);
//    }
//
//    @Bean
//    public void addStartupSubscription() {
//        Finance finance = new Finance(
//                "2c9180828bd49883018bd45c16690032",
//                BigDecimal.valueOf(10.0),
//                Currency.USD
//        );
//        finance = financeRepository.save(finance);
//
//        SubscriptionType subscriptionType = new SubscriptionType(
//                "2c9180828bd49883018bd45c36690032",
//                SubscriptionTypeIdentifier.STARTUP_PACK,
//                "startup",
//                "It is a long established fact that a reader will be distracted by the readable content " +
//                        "of a page when looking at its layout. The point of using Lorem Ipsum is that it has " +
//                        "a more-or-less normal distribution of letters, as opposed to using 'Content here, " +
//                        "content here', making it look like readable English. Many desktop publishing packages " +
//                        "and web page editors now use Lorem Ipsum as their default model text, and a search for " +
//                        "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have " +
//                        "evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
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

//    @Bean
//    public void addInvestorSubscription() {
//        Finance finance = new Finance(
//                "2c9180828bd49883018bd45c16690032",
//                BigDecimal.valueOf(10.0),
//                Currency.USD
//        );
//        finance = financeRepository.save(finance);
//
//        SubscriptionType subscriptionType = new SubscriptionType(
//                "2c9180828bd49883018bd45c36690033",
//                SubscriptionTypeIdentifier.INVESTOR_PACK,
//                "investor",
//                "It is a long established fact that a reader will be distracted by the readable content " +
//                        "of a page when looking at its layout. The point of using Lorem Ipsum is that it has " +
//                        "a more-or-less normal distribution of letters, as opposed to using 'Content here, " +
//                        "content here', making it look like readable English. Many desktop publishing packages " +
//                        "and web page editors now use Lorem Ipsum as their default model text, and a search for " +
//                        "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have " +
//                        "evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
//                finance,
//                Period.ofMonths(1)
//        );
//        subscriptionType = subscriptionTypeRepository.save(subscriptionType);
//
//
//        Subscription subscription = new Subscription(
//                "2c918082v3d49883018bd45c36690082",
//                subscriptionType
//        );
//        subscription = subscriptionRepository.save(subscription);
//    }
//
//    @Bean
//    public void addTwoSubscription() {
//        Finance finance = new Finance(
//                "2c9180828bd49883018bd45c16690032",
//                BigDecimal.valueOf(10.0),
//                Currency.USD
//        );
//        finance = financeRepository.save(finance);
//
//
//        SubscriptionType subscriptionType = new SubscriptionType(
//                "2c9180828bd49883018bd45c36690035",
//                SubscriptionTypeIdentifier.TWO_IN_ONE_PACK,
//                "two in one",
//                "It is a long established fact that a reader will be distracted by the readable content " +
//                        "of a page when looking at its layout. The point of using Lorem Ipsum is that it has " +
//                        "a more-or-less normal distribution of letters, as opposed to using 'Content here, " +
//                        "content here', making it look like readable English. Many desktop publishing packages " +
//                        "and web page editors now use Lorem Ipsum as their default model text, and a search for " +
//                        "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have " +
//                        "evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
//                finance,
//                Period.ofMonths(1)
//        );
//        subscriptionType = subscriptionTypeRepository.save(subscriptionType);
//
//
//        Subscription subscription = new Subscription(
//                "2c918082v3d49883018bd45c36690031",
//                subscriptionType
//        );
//        subscription = subscriptionRepository.save(subscription);
//    }
}
