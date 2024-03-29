package com.growandpull.api.config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    @Bean
    public PayPalHttpClient payPalHttpClient(
            @Value("${paypal.client.id}")
            String id,
            @Value("${paypal.client.secret}")
            String secret
    ) {
        return new PayPalHttpClient(new PayPalEnvironment.Sandbox(id, secret));
    }
}
