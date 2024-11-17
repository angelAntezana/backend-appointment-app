package com.backend.appointment.appointment_app.services;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(EmployeeRequest employeeRequest);
}
