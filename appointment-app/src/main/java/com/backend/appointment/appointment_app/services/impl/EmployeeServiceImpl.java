package com.backend.appointment.appointment_app.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.entity.Employee;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.exceptions.enums.EmployeeMessageError;
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
    public EmployeeDto get(Long personId) throws CustomException {
        Optional<Employee> employee = employeeRepository.findById(personId);
        if (!employee.isPresent()) {
            throw new CustomException(
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getCode(),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getMessage(personId),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getStatusCode()
                );
        } 
        return EmployeeMapper.toDto(employee.get());
    }

    @Override
    public List<EmployeeDto> getAll() throws CustomException {
        return EmployeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeRequest) throws CustomException{
        if (employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
            throw new CustomException(
                EmployeeMessageError.EMAIL_ALREADY_EXISTS.getCode(),
                EmployeeMessageError.EMAIL_ALREADY_EXISTS.getMessage(employeeRequest.getEmail()),
                EmployeeMessageError.EMAIL_ALREADY_EXISTS.getStatusCode()
            );
        }
        if (employeeRepository.findByDni(employeeRequest.getDni()).isPresent()) {
            throw new CustomException(
                EmployeeMessageError.DNI_ALREADY_EXISTS.getCode(),
                EmployeeMessageError.DNI_ALREADY_EXISTS.getMessage(employeeRequest.getDni()),
                EmployeeMessageError.DNI_ALREADY_EXISTS.getStatusCode()
            );
        }
        Employee saveEmployee = EmployeeMapper.toEntity(employeeRequest);
        Employee savedEmployee = employeeRepository.save(saveEmployee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto update(Long personId ,EmployeeDto employeeDto) throws CustomException {
        if (!employeeRepository.findById(employeeDto.getPersonId()).isPresent()) {
            throw new CustomException(
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getCode(),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getMessage(personId),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getStatusCode()
            );
        }
        Employee updatedEmployee = EmployeeMapper.toEntity(employeeDto);
        return EmployeeMapper.toDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public boolean delete(Long personId) throws CustomException {
        Optional<Employee> employee = employeeRepository.findById(personId);
        if (!employee.isPresent()) {
            throw new CustomException(
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getCode(),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getMessage(personId),
                EmployeeMessageError.EMPLOYEE_NOT_FOUND.getStatusCode()
            );
        } 
        employeeRepository.delete(employee.get());
        return true;
    }
    
}
