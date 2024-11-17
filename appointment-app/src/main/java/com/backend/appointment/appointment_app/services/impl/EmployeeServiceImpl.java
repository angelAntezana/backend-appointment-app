package com.backend.appointment.appointment_app.services.impl;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.dto.EmployeeResponse;
import com.backend.appointment.appointment_app.entity.Employee;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.mapper.EmployeeMapper;
import com.backend.appointment.appointment_app.repository.EmployeeRepository;
import com.backend.appointment.appointment_app.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) throws CustomException{
        Employee saveEmployee = EmployeeMapper.toEntity(employeeRequest);
        Employee savedEmployee = employeeRepository.save(saveEmployee);
        return EmployeeMapper.toDto(savedEmployee);
    }
    
}
