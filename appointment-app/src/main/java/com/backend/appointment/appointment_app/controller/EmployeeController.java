package com.backend.appointment.appointment_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.services.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

       @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeRequest employeeRequest) {

            System.out.println(employeeRequest);
        return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.saveEmployee(employeeRequest));

    }
    
}
