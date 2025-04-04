package com.hiraru.weatherservice.dto;

import lombok.Data;

@Data
public class Location {
    private Integer personId;
    private String city;
    private Double latitude;
    private Double longitude;
}