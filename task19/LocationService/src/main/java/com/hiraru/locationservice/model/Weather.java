package com.hiraru.locationservice.model;

import java.util.List;
import lombok.Data;

@Data
public class Weather {
    private Main main;
    private List<WeatherDescription> weather;
    
    @Data
    public static class Main {
        private Double temp;
        private Double feels_like;
        private Double temp_min;
        private Double temp_max;
        private Integer pressure;
        private Integer humidity;
    }
    
    @Data
    public static class WeatherDescription {
        private String description;
    }
    
    // Helper method to get description
    public String getDescription() {
        return !weather.isEmpty() ? weather.get(0).getDescription() : null;
    }
}