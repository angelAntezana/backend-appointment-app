package com.backend.appointment.appointment_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.services.EmployeeService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("${api-version.prefix}/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = {"personId"})
    public ResponseEntity<EmployeeDto> get(@PathVariable Long personId) throws CustomException {
        return ResponseEntity.ok(employeeService.get(personId));
    }

    @GetMapping(value = {""})
    public ResponseEntity<List<EmployeeDto>> getAll() throws CustomException{
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping(value = {""})
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeDto employeeRequest) throws CustomException{

        return ResponseEntity.ok(employeeService.create(employeeRequest));
    }

    @PutMapping(value = {"{personId}"})
    public ResponseEntity<EmployeeDto> update(@PathVariable Long personId ,@Valid @RequestBody EmployeeDto employeeRequest) throws CustomException {
        return ResponseEntity.ok(employeeService.update(personId ,employeeRequest));
    }

    @DeleteMapping(value = {"{personId}"})
    public ResponseEntity<Boolean> delete(@PathVariable("personId") Long personId) throws CustomException{
        return ResponseEntity.ok(employeeService.delete(personId));
    }
    
}
