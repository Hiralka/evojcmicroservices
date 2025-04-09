package com.hiraru.locationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {
    
    @Value("${openweather.api.key}")
    private String apiKey;
    
    @Value("${openweather.api.url}")
    private String apiUrl;
   
    
    // Getter methods if other components need these values
    public String getApiKey() {
        return apiKey;
    }
    
    public String getApiUrl() {
        return apiUrl;
    }
}