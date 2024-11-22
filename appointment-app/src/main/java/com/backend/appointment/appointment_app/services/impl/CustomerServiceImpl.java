package com.backend.appointment.appointment_app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.entity.Customer;
import com.backend.appointment.appointment_app.exceptions.CustomException;
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
    public List<CustomerDto> getAll() throws CustomException {
        return CustomerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(CustomerDto customerRequest) throws CustomException {
        if (customerRepository.findByEmail(customerRequest.getEmail()).isPresent()) {
            throw new CustomException("CUSTOMER-0000", "This email "+ customerRequest.getEmail() +" already exist", 406);
        }
        Customer saveCustomer = CustomerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(saveCustomer);
        return CustomerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) throws CustomException {
        if (!customerRepository.findById(customerDto.getPersonId()).isPresent()) {
            throw new CustomException("CUSTOMER-0002", "This customer with id " + customerDto + " does not exist", 400);
        }
        Customer updatedCustomer = CustomerMapper.toEntity(customerDto);
        return CustomerMapper.toDto(customerRepository.save(updatedCustomer));
    }

    @Override
    public boolean delete(Long personId) throws CustomException {
        if (!customerRepository.findById(personId).isPresent()) {
            throw new CustomException("CUSTOMER-0002", "This customer with id " + personId + " does not exist", 400);
        }
        customerRepository.delete(customerRepository.findById(personId).get());
        return true;
    }

}
