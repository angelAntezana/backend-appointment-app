package com.backend.appointment.appointment_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.entity.Customer;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.exceptions.enums.CustomerMessageError;
import com.backend.appointment.appointment_app.mapper.CustomerMapper;
import com.backend.appointment.appointment_app.repository.CustomerRepository;
import com.backend.appointment.appointment_app.services.impl.CustomerServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerMapper customerMapper;
    Customer customer1;
    Customer customer2;

    CustomerDto customerDto1;
    CustomerDto customerDto2;
    Pageable pageable = PageRequest.of(0, 10);


    @BeforeEach
    void setUp() {
        customer1 = Customer.builder()
        .email("prueba1@gmail.com")
        .firstName("fisrtName1")
        .secondName("secondName1")
        .firstLastName("firstLastName1")
        .secondLastName("secondLastName1")
        .personId(1L)
        .phoneNumber("111111111")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        customer2 = Customer.builder()
        .email("prueba2@gmail.com")
        .firstName("fisrtName2")
        .secondName("secondName2")
        .firstLastName("firstLastName2")
        .secondLastName("secondLastName2")
        .personId(1L)
        .phoneNumber("111111112")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        customerDto1 = CustomerDto.builder()
        .email("prueba1@gmail.com")
        .firstName("fisrtName1")
        .secondName("secondName1")
        .firstLastName("firstLastName1")
        .secondLastName("secondLastName1")
        .personId(1L)
        .phoneNumber("111111111")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        customerDto2 = CustomerDto.builder()
        .email("prueba2@gmail.com")
        .firstName("fisrtName2")
        .secondName("secondName2")
        .firstLastName("firstLastName2")
        .secondLastName("secondLastName2")
        .personId(1L)
        .phoneNumber("111111112")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

    }


    @Test
    void testGetById_CustomerExists() throws CustomException{
        Long personId = 1L;

        when(customerRepository.findById(personId)).thenReturn(Optional.of(customer1));
        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);

        CustomerDto result = customerServiceImpl.getById(personId);

        assertNotNull(result);
        assertEquals(personId, result.getPersonId());
        verify(customerRepository, times(1)).findById(personId);
    }

    @Test
    void testGetById_CustomerNotFound() {
        Long personId = 1L;

        when(customerRepository.findById(personId)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            customerServiceImpl.getById(personId);
        });

        assertEquals(CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), exception.getCode());
        verify(customerRepository, times(1)).findById(personId);
    }

    @Test
    void testSearch_WithSearchTerm() {
        // Datos de prueba
        String searchTerm = "prueba2";
        List<Customer> listCustomer = new ArrayList<Customer>(List.of(customer2));
        Page<Customer> mockPage = new PageImpl<>(listCustomer);

        // Simulación del comportamiento del repositorio
        when(customerRepository.findBySearchTerm(searchTerm, pageable)).thenReturn(mockPage);
        when(customerMapper.toDto(customer2)).thenReturn(customerDto2);

        // Llamar al método
        Page<CustomerDto> result = customerServiceImpl.getBySearchTerm(searchTerm, pageable);

        // Verificaciones
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(customer2.getFirstName(), result.getContent().get(0).getFirstName());

        // Verificar interacción con el repositorio
        verify(customerRepository).findBySearchTerm(eq(searchTerm), eq(pageable));
    }

    @Test
    void testSearch_WithoutSearchTerm() {
        // Datos de prueba
        String searchTerm = null;
        List<Customer> listCustomer = new ArrayList<Customer>(List.of(customer1,customer2));
        Page<Customer> mockPage = new PageImpl<>(listCustomer);

        // Simulación del comportamiento del repositorio
        when(customerRepository.findAll(pageable)).thenReturn(mockPage);
        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);
        when(customerMapper.toDto(customer2)).thenReturn(customerDto2);

        // Llamar al método
        Page<CustomerDto> result = customerServiceImpl.getBySearchTerm(searchTerm, pageable);

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(customer1.getFirstName(), result.getContent().get(0).getFirstName());
        assertEquals(customer2.getFirstName(), result.getContent().get(1).getFirstName());

        // Verificar interacción con el repositorio
        verify(customerRepository).findAll(eq(pageable));
    }
}
