package com.growandpull.api.config;

import com.growandpull.api.model.entity.Category;
import com.growandpull.api.model.entity.Finance;
import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.model.entity.User;
import com.growandpull.api.model.enums.AdStatus;
import com.growandpull.api.model.enums.Currency;
import com.growandpull.api.model.enums.Role;
import com.growandpull.api.model.enums.StartupStatus;
import com.growandpull.api.repository.CategoryRepository;
import com.growandpull.api.repository.FinanceRepository;
import com.growandpull.api.repository.StartupRepository;
import com.growandpull.api.repository.UserRepository;
import com.growandpull.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final UserService userService;
    private final StartupRepository startupRepository;
    private final CategoryRepository categoryRepository;
    private final FinanceRepository financeRepository;
    private final UserRepository userRepository;


//    @Bean
//    public void generate() {
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
//        Category category = new Category("category");
//        Finance finance = new Finance(BigDecimal.TEN, Currency.EUR);
//
//        user = userRepository.save(user);
//        category = categoryRepository.save(category);
//        finance = financeRepository.save(finance);
//
//        List<Startup> startups = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            startups.add(new Startup(
//                    "title",
//                    user,
//                    "description" + i,
//                    StartupStatus.IDEA,
//                    category,
//                    finance,
//                    AdStatus.ENABLED,
//                    null,
//                    LocalDateTime.now()
//            ));
//        }
//        startupRepository.saveAll(startups);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.findUserByEmail(username);
            System.out.println("USER:");
            System.out.println(user);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
        };
    }
}
