package com.hiraru.weatherservice.controller;

import com.hiraru.weatherservice.dto.*;
import com.hiraru.weatherservice.model.*;
import com.hiraru.weatherservice.repository.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Weather> addWeatherData(@RequestBody Weather weather) {
        Weather savedWeather = weatherRepository.save(weather);
        return new ResponseEntity<>(savedWeather, HttpStatus.CREATED);
    }


    @GetMapping("/{personId}/info")
    public ResponseEntity<Map<String, Object>> getFullWeatherInfo(@PathVariable Integer personId) {
        try {

            Person person = restTemplate.getForObject(
                "http://localhost:8080/person/" + personId, 
                Person.class
            );
            if (person == null) {
                return ResponseEntity.notFound().build();
            }


            Location location = restTemplate.getForObject(
                "http://localhost:8081/location/" + personId,
                Location.class
            );
            if (location == null) {
                return ResponseEntity.notFound().build();
            }

            Weather weather = weatherRepository.findByLatitudeAndLongitude(
                location.getLatitude(),
                location.getLongitude()
            ).orElseThrow(() -> new RuntimeException("Данные о погоде не найдены"));

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("person", Map.of(
                "id", person.getId(),
                "name", person.getName(),
                "city", location.getCity()
            ));
            response.put("weather", Map.of(
                "temperature", weather.getTemperature(),
                "description", weather.getDescription(),
                "coordinates", Map.of(
                    "latitude", weather.getLatitude(),
                    "longitude", weather.getLongitude()
                )
            ));

            return ResponseEntity.ok(response);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", e.getMessage()));
        }
    }
}