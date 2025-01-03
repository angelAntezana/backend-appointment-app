package com.backend.appointment.appointment_app.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.entity.Customer;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.exceptions.enums.CustomerMessageError;
import com.backend.appointment.appointment_app.mapper.CustomerMapper;
import com.backend.appointment.appointment_app.repository.CustomerRepository;
import com.backend.appointment.appointment_app.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;
    
    public CustomerServiceImpl(CustomerRepository customerRepository,
    CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getById(Long personId) throws CustomException {
        Optional<Customer> customer = customerRepository.findById(personId);
        if (!customer.isPresent()) {
            throw new CustomException(
            CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getMessage(personId), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getStatusCode()
            );
        }
        return customerMapper.toDto(customer.get());
    }

    @Override
    public Page<CustomerDto> getBySearchTerm(String searchTerm, Pageable pageable) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return customerRepository.findAll(pageable)
                .map(customerMapper::toDto);
        }

        return customerRepository.findBySearchTerm(searchTerm, pageable)
            .map(customerMapper::toDto);
    }

    @Override
    public Page<CustomerDto> getAll(Pageable pageable) throws CustomException {
         return customerRepository.findAll(pageable)
         .map(customerMapper::toDto);
    }

    @Override
    public CustomerDto create(CustomerDto customerRequest) throws CustomException {
        if (customerRepository.findByEmail(customerRequest.getEmail()).isPresent()) {
            throw new CustomException(
            CustomerMessageError.EMAIL_ALREADY_EXISTS.getCode(), 
            CustomerMessageError.EMAIL_ALREADY_EXISTS.getMessage(customerRequest.getEmail()), 
            CustomerMessageError.EMAIL_ALREADY_EXISTS.getStatusCode()
            );
        }
        Customer saveCustomer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(saveCustomer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDto update(Long personId,CustomerDto customerDto) throws CustomException {
        if (!customerRepository.findById(personId).isPresent()) {
            throw new CustomException(
                CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), 
                CustomerMessageError.CUSTOMER_NOT_FOUND.getMessage(personId), 
                CustomerMessageError.CUSTOMER_NOT_FOUND.getStatusCode()
                );
        }
        Customer updatedCustomer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(updatedCustomer));
    }

    @Override
    public boolean delete(Long personId) throws CustomException {
        Optional<Customer> customer = customerRepository.findById(personId);
        if (!customer.isPresent()) {
            throw new CustomException(
            CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getMessage(personId), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getStatusCode()
            );
        }
        customerRepository.delete(customer.get());
        return true;
    }

}
