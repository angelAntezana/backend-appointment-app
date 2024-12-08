package com.backend.appointment.appointment_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.AppointmentDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.services.AppointmentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("${api-version.prefix}/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping(value = {""})
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto appointmentRequest) throws CustomException{
        
        return ResponseEntity.ok(appointmentService.create(appointmentRequest));
    }
    
}
