package com.hiraru.locationservice.controller;

import com.hiraru.locationservice.model.Location;
import com.hiraru.locationservice.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationRepository repository;
    private final RestTemplate restTemplate;

    public LocationController(LocationRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
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
    public ResponseEntity<String> getWeather(@RequestParam String name) {
        return repository.findByName(name)
                .map(location -> {
                    String weatherServiceUrl = "http://weather-service/weather?lat=" + 
                            location.getLatitude() + "&lon=" + location.getLongitude();
                    return ResponseEntity.ok(restTemplate.getForObject(weatherServiceUrl, String.class));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}