package com.hiraru.locationservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LocationConfig {
    
    @Bean  // Для внешних API (без балансировки)
    public RestTemplate plainRestTemplate() {
        return new RestTemplate();
    }
}