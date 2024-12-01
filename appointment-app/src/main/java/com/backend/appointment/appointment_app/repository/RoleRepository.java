package com.backend.appointment.appointment_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Optional<Role> findByName(String name);
}
