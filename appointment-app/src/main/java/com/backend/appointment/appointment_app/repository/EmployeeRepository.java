package com.backend.appointment.appointment_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByDni(String dni);
}
