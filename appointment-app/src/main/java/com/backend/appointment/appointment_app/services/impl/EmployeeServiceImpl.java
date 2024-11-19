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
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) throws CustomException{
        if (employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
            throw new CustomException("EMPLOYEE-0000", "This email "+ employeeRequest.getEmail() +" already exist", 406);
        }
        if (employeeRepository.findByDni(employeeRequest.getDni()).isPresent()) {
            throw new CustomException("EMPLOYEE-0001", "This dni " + employeeRequest.getDni() +" already exist", 406);
        }
        Employee saveEmployee = EmployeeMapper.toEntity(employeeRequest);
        Employee savedEmployee = employeeRepository.save(saveEmployee);
        return EmployeeMapper.toDto(savedEmployee);
    }
    
}
