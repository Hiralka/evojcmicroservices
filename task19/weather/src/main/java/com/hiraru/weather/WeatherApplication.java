/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hiraru.weather;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class WeatherApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WeatherApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8084"));
        app.run(args);
    }
}
