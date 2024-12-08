package com.backend.appointment.appointment_app.services.impl;

import java.util.List;
import java.util.Optional;

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

    private CustomerRepository customerRepository;
    
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto get(Long personId) throws CustomException {
        Optional<Customer> customer = customerRepository.findById(personId);
        if (!customer.isPresent()) {
            throw new CustomException(
            CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getMessage(personId), 
            CustomerMessageError.CUSTOMER_NOT_FOUND.getStatusCode()
            );
        }
        return CustomerMapper.toDto(customer.get());
    }

    @Override
    public List<CustomerDto> getAll() throws CustomException {
        return CustomerMapper.toDtoList(customerRepository.findAll());
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
        Customer saveCustomer = CustomerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(saveCustomer);
        return CustomerMapper.toDto(savedCustomer);
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
        Customer updatedCustomer = CustomerMapper.toEntity(customerDto);
        return CustomerMapper.toDto(customerRepository.save(updatedCustomer));
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
