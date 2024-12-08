package com.backend.appointment.appointment_app.mapper;

import java.util.ArrayList;
import java.util.List;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.entity.Customer;

public class CustomerMapper {
    
    private CustomerMapper() {
        throw new IllegalStateException("Mapper customer class");
    }

    public static Customer toEntity(CustomerDto customerRequest) {
        if (customerRequest == null) {
            return null;
        }
        Customer customer = Customer.builder()
        .firstName(customerRequest.getFirstName())
        .secondName(customerRequest.getSecondName())
        .firstLastName(customerRequest.getFirstLastName())
        .secondLastName(customerRequest.getSecondLastName())
        .email(customerRequest.getEmail())
        .phoneNumber(customerRequest.getPhoneNumber())
        .sendNotificationEmail(customerRequest.isSendNotificationEmail())
        .sendNotificationPhoneNumber(customerRequest.isSendNotificationPhoneNumber())
        .build();
        // WHEN A CUSTOMER IS CREATED
        if (customerRequest.getPersonId() == null) {
            return customer;
        }
        // UPDATE CUSTOMER
        customer.setPersonId(customerRequest.getPersonId());
        return customer;
        
    }

    public static CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
        .personId(customer.getPersonId())
        .firstName(customer.getFirstName())
        .secondName(customer.getSecondName())
        .firstLastName(customer.getFirstLastName())
        .secondLastName(customer.getSecondLastName())
        .email(customer.getEmail())
        .phoneNumber(customer.getPhoneNumber())
        .sendNotificationEmail(customer.isSendNotificationEmail())
        .sendNotificationPhoneNumber(customer.isSendNotificationPhoneNumber())
        .build();
    }

    public static List<CustomerDto> toDtoList(List<Customer> listCustomer) {
        List<CustomerDto> listCustomerDtos = new ArrayList<>();

         for (Customer customer: listCustomer) {
            listCustomerDtos.add(toDto(customer));
        }
        return listCustomerDtos;
    }
}
