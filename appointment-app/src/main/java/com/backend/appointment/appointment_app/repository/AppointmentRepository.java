package com.backend.appointment.appointment_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appointment.appointment_app.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    
}
