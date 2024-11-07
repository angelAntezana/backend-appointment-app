package com.backend.appointment.appointment_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
