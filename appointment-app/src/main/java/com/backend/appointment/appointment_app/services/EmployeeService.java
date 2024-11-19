package com.backend.appointment.appointment_app.services;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.dto.EmployeeResponse;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest) throws CustomException;
}
