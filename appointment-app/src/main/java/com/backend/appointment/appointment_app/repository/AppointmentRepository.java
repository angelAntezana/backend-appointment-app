package com.backend.appointment.appointment_app.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.appointment.appointment_app.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    
    @Query("SELECT COUNT(a) FROM appointment a " +
       "WHERE a.customer.id = :customerId " +
       "AND a.initDate < :end " +
       "AND a.endDate > :start")
    int countCustomerAppointmentsInInterval(@Param("customerId") Long customerId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(a) FROM appointment a " +
    "WHERE a.employee.id = :employeeId " +
    "AND a.initDate < :end " +
    "AND a.endDate > :start")
    int countEmployeeAppointmentsInInterval(@Param("employeeId") Long employeeId,
                                        @Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);
                                 



}
