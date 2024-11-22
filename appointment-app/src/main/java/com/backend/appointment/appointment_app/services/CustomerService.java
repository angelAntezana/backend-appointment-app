package com.backend.appointment.appointment_app.services;

import java.util.List;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface CustomerService {
    
    List<CustomerDto> getAll() throws CustomException;

    CustomerDto create(CustomerDto customerRequest) throws CustomException;

    CustomerDto update(CustomerDto customerDto) throws CustomException;

    boolean delete(Long personId) throws CustomException;
}
