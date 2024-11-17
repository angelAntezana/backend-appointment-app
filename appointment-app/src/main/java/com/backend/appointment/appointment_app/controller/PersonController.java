package com.backend.appointment.appointment_app.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.services.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/")
    public ResponseEntity<?> getAllPerson() throws IOException {
        try {
        return ResponseEntity.status(HttpStatus.OK)
        .body(personService.getAllPerson());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"zzzzzzzzzzzzzzzzzzz");
    }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add() throws IOException {
        try {
        return ResponseEntity.status(HttpStatus.OK)
        .body(personService.getAllPerson());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"zzzzzzzzzzzzzzzzzzz");
    }
    }
}
