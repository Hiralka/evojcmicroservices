/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hiraru.weather.controller;

/**
 *
 * @author hiraru
 */
import com.hiraru.weather.model.Root;
import com.hiraru.weather.config.WeatherConfig;
import com.hiraru.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;
    
    @Autowired
    public WeatherController(RestTemplate restTemplate, WeatherConfig weatherConfig) {
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }

@GetMapping
    public ResponseEntity<Weather> getWeather(
            @RequestParam double lat,
            @RequestParam double lon) {
        
        String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric",
                weatherConfig.getApiUrl(),
                lat,
                lon,
                weatherConfig.getApiKey());
        
        Weather response = restTemplate.getForObject(url, Weather.class);
        return ResponseEntity.ok(response);
    }
  
}
