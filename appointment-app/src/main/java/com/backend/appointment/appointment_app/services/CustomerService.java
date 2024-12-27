package com.backend.appointment.appointment_app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface CustomerService {

    CustomerDto getById(Long personId) throws CustomException;

    Page<CustomerDto>getBySearchTerm(String searchTerm, Pageable pageable);
    
    Page<CustomerDto> getAll(Pageable pageable) throws CustomException;

    CustomerDto create(CustomerDto customerRequest) throws CustomException;

    CustomerDto update(Long personId, CustomerDto customerDto) throws CustomException;

    boolean delete(Long personId) throws CustomException;
}
