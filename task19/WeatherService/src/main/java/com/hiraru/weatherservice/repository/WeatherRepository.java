package com.hiraru.weatherservice.repository;

import com.hiraru.weatherservice.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByLatitudeAndLongitude(Double latitude, Double longitude);
}