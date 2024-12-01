package com.backend.appointment.appointment_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api-version.prefix}/customer")
public class CustomerController {
    
    private CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {""})
    public ResponseEntity<?> getAll() throws CustomException {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerRequest) throws CustomException {
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @PostMapping(value = { "/update" })
    public ResponseEntity<?> update(@Valid @RequestBody CustomerDto customerRequest) throws CustomException{
        return ResponseEntity.ok(customerService.update(customerRequest));
    }

    @DeleteMapping(value = { "/delete/{personId}" })
    public ResponseEntity<?> delete(@PathVariable("personId") Long personId) throws CustomException {
        return ResponseEntity.ok(customerService.delete(personId));
    }
}
