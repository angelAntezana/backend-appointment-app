package com.backend.appointment.appointment_app.services;

import java.util.List;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface CustomerService {

    CustomerDto get(Long personId) throws CustomException;
    
    List<CustomerDto> getAll() throws CustomException;

    CustomerDto create(CustomerDto customerRequest) throws CustomException;

    CustomerDto update(Long personId, CustomerDto customerDto) throws CustomException;

    boolean delete(Long personId) throws CustomException;
}
