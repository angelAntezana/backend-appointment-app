package com.backend.appointment.appointment_app.services.impl;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.entity.Employee;
import com.backend.appointment.appointment_app.repository.EmployeeRepository;
import com.backend.appointment.appointment_app.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        // Crear una instancia de Employee y asignar los valores directamente
        Employee saveEmployee = new Employee();
        saveEmployee.setFirstName(employeeRequest.getFirstName());
        saveEmployee.setSecondName(employeeRequest.getSecondName());
        saveEmployee.setFirstLastName(employeeRequest.getFirstLastName());
        saveEmployee.setSecondLastName(employeeRequest.getSecondLastName());
        saveEmployee.setEmail(employeeRequest.getEmail());
        saveEmployee.setPhoneNumber(employeeRequest.getPhoneNumber());
        saveEmployee.setDni(employeeRequest.getDni());
        
        // Guardar el empleado en la base de datos
        return employeeRepository.save(saveEmployee);
    }
    
}
