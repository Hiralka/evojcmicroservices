package com.hiraru.personservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    private String location; 
}