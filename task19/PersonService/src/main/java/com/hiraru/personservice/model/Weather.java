package com.hiraru.personservice.model;

import lombok.Data;

@Data
public class Weather {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer pressure;
    private Integer humidity;
    private String description;
}