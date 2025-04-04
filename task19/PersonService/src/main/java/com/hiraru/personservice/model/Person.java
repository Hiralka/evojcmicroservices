package com.hiraru.personservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import jakarta.persistence.Entity;   
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @Id @GeneratedValue
    private int id;

    @NonNull private String name;
    @NonNull private String location;

    public Person(@NonNull String name, @NonNull String location) {
        this.name = name;
        this.location = location;
    }
}