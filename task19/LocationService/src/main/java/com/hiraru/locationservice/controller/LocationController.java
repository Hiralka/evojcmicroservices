package com.hiraru.locationservice.controller;

import com.hiraru.locationservice.config.WeatherConfig;
import com.hiraru.locationservice.model.Location;
import com.hiraru.locationservice.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final WeatherConfig weatherConfig;
    @Qualifier("plainRestTemplate") 
    private final RestTemplate restTemplate;
    private final LocationRepository repository;

 @Autowired
    public LocationController(
            WeatherConfig weatherConfig,
            RestTemplate restTemplate,
            LocationRepository repository
    ) {
        this.weatherConfig = weatherConfig;
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    @GetMapping(params = "name")
    public ResponseEntity<Location> getLocationByName(@RequestParam String name) {
        return repository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        if (repository.findByName(location.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(location));
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(
            @RequestParam String name,
            @RequestBody Location locationDetails) {
        return repository.findByName(name)
                .map(existing -> {
                    existing.setName(locationDetails.getName());
                    existing.setLatitude(locationDetails.getLatitude());
                    existing.setLongitude(locationDetails.getLongitude());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteLocation(@RequestParam String name) {
        return repository.findByName(name)
                .map(location -> {
                    repository.delete(location);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

@GetMapping("/weather")
public ResponseEntity<String> getWeatherByCityName(@RequestParam String name) {
    return repository.findByName(name)
            .map(location -> {
                String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric",
                        weatherConfig.getApiUrl(),
                        location.getLatitude(),
                        location.getLongitude(),
                        weatherConfig.getApiKey());
                
                String jsonResponse = restTemplate.getForObject(url, String.class);
                

                String prettyJson = jsonResponse
                        .replace(",", ",\n  ")
                        .replace("{", "{\n  ")
                        .replace("}", "\n}");
                
                return ResponseEntity.ok(prettyJson);
            })
            .orElse(ResponseEntity.notFound().build());
}
}