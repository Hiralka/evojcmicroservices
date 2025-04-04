package com.hiraru.locationservice.controller;

import com.hiraru.locationservice.model.Location;
import com.hiraru.locationservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        try {
            Location savedLocation = repository.save(location);
            return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Location> getLocation(@PathVariable Integer personId) {
        Location location = repository.findById(personId).orElse(null);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}