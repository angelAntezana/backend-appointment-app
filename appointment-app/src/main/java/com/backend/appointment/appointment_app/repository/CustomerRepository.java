package com.backend.appointment.appointment_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    Optional<Customer> findByEmail(String email);
    
}
