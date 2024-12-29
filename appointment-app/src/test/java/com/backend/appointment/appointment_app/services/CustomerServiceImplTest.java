package com.backend.appointment.appointment_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
import org.springframework.data.domain.Sort;
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
class CustomerServiceImplTest {
    
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerMapper customerMapper;
    Customer customer1;
    Customer customer2;
    Customer customerCreate;

    CustomerDto customerDto1;
    CustomerDto customerDto2;
    List<String> sortBy = new ArrayList<>(List.of("firstName"));
    List<String> direction = new ArrayList<>(List.of("asc"));

    Pageable pageable;

    @BeforeEach
    void setUp() {
        Sort sort = Sort.by(
            IntStream.range(0, sortBy.size())
                .mapToObj(i -> {
                    String field = sortBy.get(i);
                    String orderDirection = direction.get(i);
                    return orderDirection.equalsIgnoreCase("desc") ? Sort.Order.desc(field) : Sort.Order.asc(field);
                })
                .toList()
        );
        pageable = PageRequest.of(0, 10, sort);
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

        customerCreate = Customer.builder()
        .email("prueba3@gmail.com")
        .firstName("fisrtName3")
        .secondName("secondName3")
        .firstLastName("firstLastName3")
        .secondLastName("secondLastName3")
        .personId(3L)
        .phoneNumber("111111113")
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
        verify(customerRepository).findBySearchTerm(searchTerm, pageable);
    }

    @Test
    void testSearch_WithNullSearchTerm() {
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
        assertEquals(null, searchTerm);
        assertEquals(2, result.getContent().size());
        assertEquals(customer1.getFirstName(), result.getContent().get(0).getFirstName());
        assertEquals(customer2.getFirstName(), result.getContent().get(1).getFirstName());

        // Verificar interacción con el repositorio
        verify(customerRepository).findAll(pageable);
    }

    @Test
    void testSearch_WithEmptySearchTerm() {
        // Datos de prueba
        String searchTerm = "";
        List<Customer> listCustomer = new ArrayList<>(List.of(customer1, customer2));
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
        verify(customerRepository).findAll(pageable);
    }

    @Test
    void testGetAll() throws CustomException{
        List<Customer> listCustomer = new ArrayList<Customer>(List.of(customer1, customer2));
        Page<Customer> mockPage = new PageImpl<>(listCustomer);

        when(customerRepository.findAll(pageable)).thenReturn(mockPage);
        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);
        when(customerMapper.toDto(customer2)).thenReturn(customerDto2);

        Page<CustomerDto> result = customerServiceImpl.getAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(customer1.getFirstName(), result.getContent().get(0).getFirstName());
        assertEquals(customer2.getFirstName(), result.getContent().get(1).getFirstName());

        // Verificar interacción con el repositorio
        verify(customerRepository).findAll(pageable);
    }

    @Test
    void testCreate_CustomerAlreadyExists(){
        CustomerDto customerNew = CustomerDto.builder()
        .email("prueba3@gmail.com")
        .firstName("firstName3")
        .secondName("secondName3")
        .firstLastName("firstLastName3")
        .secondLastName("secondLastName3")
        .phoneNumber("111111113")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        when(customerRepository.findByEmail(customerNew.getEmail())).thenReturn(Optional.of(customerCreate));
        
        CustomException exception = assertThrows(CustomException.class, () -> {
            customerServiceImpl.create(customerNew);
        });
        assertEquals(CustomerMessageError.EMAIL_ALREADY_EXISTS.getCode(), exception.getCode());
        verify(customerRepository, times(1)).findByEmail(customerNew.getEmail());
    }

    @Test
    void testCreate_CustomerSuccessfullyCreated() throws CustomException{
        CustomerDto customerNew = CustomerDto.builder()
        .email("prueba3@gmail.com")
        .firstName("firstName3")
        .secondName("secondName3")
        .firstLastName("firstLastName3")
        .secondLastName("secondLastName3")
        .phoneNumber("111111113")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        when(customerRepository.findByEmail(customerNew.getEmail())).thenReturn(Optional.empty());
        
        when(customerRepository.save(any(Customer.class))).thenReturn(customerCreate);
        when(customerMapper.toEntity(customerNew)).thenReturn(customerCreate);
        when(customerMapper.toDto(customerCreate)).thenReturn(customerNew);
        CustomerDto result = customerServiceImpl.create(customerNew);

        assertNotNull(result);
        assertEquals(customerNew.getEmail(), result.getEmail());
        verify(customerRepository, times(1)).findByEmail(customerNew.getEmail());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdate_CustomerNotFound() {
        Long personId = 1L;

        when(customerRepository.findById(personId)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            customerServiceImpl.update(personId, customerDto1);
        });

        assertEquals(CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), exception.getCode());
        verify(customerRepository, times(1)).findById(personId);
    }

    @Test
    void testUpdate_CustomerSuccessfullyUpdated() throws CustomException {
        Long personId = 3L;
        CustomerDto customerNew = CustomerDto.builder()
        .personId(3L)
        .email("prueba3@gmail.com")
        .firstName("firstName3")
        .secondName("secondName3")
        .firstLastName("firstLastName3")
        .secondLastName("secondLastName3")
        .phoneNumber("111111113")
        .sendNotificationEmail(true)
        .sendNotificationPhoneNumber(false)
        .build();

        when(customerRepository.findById(personId)).thenReturn(Optional.of(customerCreate));
        
        when(customerMapper.toEntity(customerNew)).thenReturn(customerCreate);
        when(customerMapper.toDto(customerCreate)).thenReturn(customerNew);
        when(customerRepository.save(any(Customer.class))).thenReturn(customerCreate);

        CustomerDto result = customerServiceImpl.update(personId,customerNew);

        assertNotNull(result);
        assertEquals(customerNew.getPersonId(), result.getPersonId());
        verify(customerRepository, times(1)).findById(personId);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testDelete_CustomerNotFound() {
        Long personId = 3L;

        when(customerRepository.findById(personId)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            customerServiceImpl.delete(personId);
        });

        assertEquals(CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(), exception.getCode());
        verify(customerRepository, times(1)).findById(personId);
    }

    @Test
    void testDelete_CustomerSuccessfullyDeleted() throws CustomException{
        Long personId = 3L;

        when(customerRepository.findById(personId)).thenReturn(Optional.of(customerCreate));

        Boolean result = customerServiceImpl.delete(personId);

        assertEquals(true, result);
        verify(customerRepository, times(1)).delete(customerCreate);
    }
}
