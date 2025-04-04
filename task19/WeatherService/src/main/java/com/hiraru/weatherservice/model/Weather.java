package com.hiraru.weatherservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;   
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue
    private Long id;
    private Double latitude;    
    private Double longitude;   
    private String description; 
    private Double temperature; 
}