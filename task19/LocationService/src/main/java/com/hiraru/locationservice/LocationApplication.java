package com.hiraru.locationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationApplication.class, args);
    }
}