package com.backend.appointment.appointment_app.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
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
    public List<EmployeeDto> getAll() throws CustomException {
        return EmployeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeRequest) throws CustomException{
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

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws CustomException {
        if (!employeeRepository.findById(employeeDto.getPersonId()).isPresent()) {
            throw new CustomException("EMPLOYEE-0002", "This employee with id " + employeeDto + " does not exist", 400);
        }
        Employee updatedEmployee = EmployeeMapper.toEntity(employeeDto);
        return EmployeeMapper.toDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public boolean delete(Long personId) throws CustomException {
        if (!employeeRepository.findById(personId).isPresent()) {
            throw new CustomException("EMPLOYEE-0002", "This employee with id" + personId + "does not exist", 400);
        } 
        employeeRepository.delete(employeeRepository.findById(personId).get());
        return true;
    }
    
}
