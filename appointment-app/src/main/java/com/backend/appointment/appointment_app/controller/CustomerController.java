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

    @GetMapping(value = {"{personId}"})
    public ResponseEntity<CustomerDto> get(@PathVariable Long personId) throws CustomException {
        return ResponseEntity.ok(customerService.get(personId));
    }

    @GetMapping(value = {""})
    public ResponseEntity<List<CustomerDto>> getAll() throws CustomException {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping(value = {""})
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto customerRequest) throws CustomException {
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @PutMapping(value = { "{personId}" })
    public ResponseEntity<CustomerDto> update(@PathVariable Long personId, @Valid @RequestBody CustomerDto customerRequest) throws CustomException{
        return ResponseEntity.ok(customerService.update(personId,customerRequest));
    }

    @DeleteMapping(value = { "{personId}" })
    public ResponseEntity<Boolean> delete(@PathVariable Long personId) throws CustomException {
        return ResponseEntity.ok(customerService.delete(personId));
    }
}
