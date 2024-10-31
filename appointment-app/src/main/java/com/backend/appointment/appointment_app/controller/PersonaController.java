package com.backend.appointment.appointment_app.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/imagen")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaRepository personaRepository;

        @GetMapping("/todas")
    public ResponseEntity<?> todasLasImagenes() throws IOException {
        try {
        return ResponseEntity.status(HttpStatus.OK)
        .body(personaRepository.findAll().stream().map(persona -> "hol" + persona.getNombre()));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"zzzzzzzzzzzzzzzzzzz");
    }
    }
}
