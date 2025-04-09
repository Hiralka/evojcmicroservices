package com.hiraru.personservice.repository;

import com.hiraru.personservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}