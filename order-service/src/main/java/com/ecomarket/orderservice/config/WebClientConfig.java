package com.ecomarket.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.inventory-service.url}")
    private String invUrl;

    @Value("${app.coupon-service.url}")
    private String couponUrl;

    @Value("${app.billing-service.url}")
    private String billingUrl;

    @Bean
    public WebClient inventoryWebClient() {
        return WebClient.builder().baseUrl(invUrl).build();
    }

    @Bean
    public WebClient couponWebClient() {
        return WebClient.builder().baseUrl(couponUrl).build();
    }

    @Bean
    public WebClient billingWebClient() {
        return WebClient.builder().baseUrl(billingUrl).build();
    }
}