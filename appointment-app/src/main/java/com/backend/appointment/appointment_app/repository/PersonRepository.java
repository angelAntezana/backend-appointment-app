package com.backend.appointment.appointment_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
