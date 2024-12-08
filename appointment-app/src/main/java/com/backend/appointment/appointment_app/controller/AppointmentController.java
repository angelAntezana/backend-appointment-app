package com.backend.appointment.appointment_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.AppointmentRequest;


@RestController
@RequestMapping("${api-version.prefix}/appointment")
public class AppointmentController {
    @PostMapping(value = {"/add"})
    public ResponseEntity<AppointmentRequest> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        
        System.out.println(appointmentRequest);
        return ResponseEntity.ok(appointmentRequest);
    }
    
}
