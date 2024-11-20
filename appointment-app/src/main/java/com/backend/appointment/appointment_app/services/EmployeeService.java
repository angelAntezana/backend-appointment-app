package com.backend.appointment.appointment_app.services;

import java.util.List;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface EmployeeService {

    List<EmployeeDto> getAll() throws CustomException;

    EmployeeDto create(EmployeeDto employeeRequest) throws CustomException;

    EmployeeDto update(EmployeeDto employeeDto) throws CustomException;

    boolean delete(Long personId) throws CustomException;
}
