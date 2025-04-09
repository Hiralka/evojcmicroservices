package com.hiraru.personservice.controller;

import com.hiraru.personservice.config.ServiceProperties;
import com.hiraru.personservice.model.Person;
import com.hiraru.personservice.model.Weather;
import com.hiraru.personservice.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository repository;
    private final RestTemplate restTemplate;
    private final String locationServiceUrl;  // Changed from hardcoded URL

    @Autowired
    public PersonController(
            PersonRepository repository,
            RestTemplate restTemplate,
            ServiceProperties serviceProperties) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.locationServiceUrl = serviceProperties.getLocation().getBaseUrl();
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        return repository.findById(id)
                .map(person -> {
                    person.setFirstname(personDetails.getFirstname());
                    person.setSurname(personDetails.getSurname());
                    person.setLastname(personDetails.getLastname());
                    person.setBirthday(personDetails.getBirthday());
                    person.setLocation(personDetails.getLocation());
                    return ResponseEntity.ok(repository.save(person));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        return repository.findById(id)
                .map(person -> {
                    repository.delete(person);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/weather")
    public ResponseEntity<Weather> getWeatherForPerson(@PathVariable Long id) {
        return repository.findById(id)
                .map(person -> {
                    String url = locationServiceUrl + "/location?name=" + person.getLocation();
                    Weather weather = restTemplate.getForObject(url, Weather.class);
                    return ResponseEntity.ok(weather);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}