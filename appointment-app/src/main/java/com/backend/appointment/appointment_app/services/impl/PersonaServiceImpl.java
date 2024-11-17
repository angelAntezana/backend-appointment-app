package com.backend.appointment.appointment_app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.entity.Person;
import com.backend.appointment.appointment_app.repository.PersonRepository;
import com.backend.appointment.appointment_app.services.PersonService;

@Service
public class PersonaServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    public PersonaServiceImpl (PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override
    public List<Person> getAllPerson() {
        try {
            return personRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
