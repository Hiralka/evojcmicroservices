package com.hiraru.locationservice.repository;

import com.hiraru.locationservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}