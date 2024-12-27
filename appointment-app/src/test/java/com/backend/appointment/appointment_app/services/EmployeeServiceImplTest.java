package com.backend.appointment.appointment_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.entity.Employee;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.repository.EmployeeRepository;
import com.backend.appointment.appointment_app.services.impl.EmployeeServiceImpl;

@ActiveProfiles("test")
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee_shouldSaveEmployee_whenValidRequest() throws CustomException {
        // Arrange
        EmployeeDto request = new EmployeeDto();
        request.setEmail("test@example.com");
        request.setDni("12345678");
        request.setFirstName("Test employee");

        Employee employee = new Employee();
        employee.setEmail(request.getEmail());
        employee.setDni(request.getDni());
        employee.setFirstName(request.getFirstName());

        Employee savedEmployee = new Employee();
        savedEmployee.setPersonId(1L);
        savedEmployee.setEmail(request.getEmail());
        savedEmployee.setDni(request.getDni());
        savedEmployee.setFirstName(request.getFirstName());

        when(employeeRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.findByDni(request.getDni())).thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        // Act
        EmployeeDto response = employeeService.create(request);

        // Assert
        assertNotNull(response);
        assertEquals(savedEmployee.getPersonId(), response.getPersonId());
        assertEquals(savedEmployee.getEmail(), response.getEmail());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void saveEmployee_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        EmployeeDto request = new EmployeeDto();
        request.setEmail("test@example.com");
        request.setDni("12345678");

        when(employeeRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            employeeService.create(request);
        });

        assertEquals("EMPLOYEE-0000", exception.getCode());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void saveEmployee_shouldThrowException_whenDniAlreadyExists() {
        // Arrange
        EmployeeDto request = new EmployeeDto();
        request.setEmail("test@example.com");
        request.setDni("12345678");

        when(employeeRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.findByDni(request.getDni())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            employeeService.create(request);
        });

        assertEquals("EMPLOYEE-0001", exception.getCode());
        verify(employeeRepository, never()).save(any(Employee.class));
    }
}
