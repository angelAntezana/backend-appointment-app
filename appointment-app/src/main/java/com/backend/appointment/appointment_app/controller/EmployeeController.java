package com.backend.appointment.appointment_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.services.EmployeeService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {""})
    public ResponseEntity<?> getAll() throws CustomException{
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeDto employeeRequest) throws CustomException{

        return ResponseEntity.ok(employeeService.create(employeeRequest));
    }

    @PostMapping(value = {"/update"})
    public ResponseEntity<?> update(@Valid @RequestBody EmployeeDto employeeRequest) throws CustomException {
        return ResponseEntity.ok(employeeService.update(employeeRequest));
    }

    @DeleteMapping(value = {"/delete/{personId}"})
    public ResponseEntity<?> delete(@PathVariable("personId") Long personId) throws CustomException{
        return ResponseEntity.ok(employeeService.delete(personId));
    }
    
}
