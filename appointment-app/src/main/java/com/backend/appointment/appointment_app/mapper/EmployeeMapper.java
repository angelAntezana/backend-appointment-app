package com.backend.appointment.appointment_app.mapper;

import com.backend.appointment.appointment_app.dto.EmployeeRequest;
import com.backend.appointment.appointment_app.dto.EmployeeResponse;
import com.backend.appointment.appointment_app.entity.Employee;

public class EmployeeMapper {
    
    public static Employee toEntity(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            return null;
        }
        Employee employee = Employee.builder()
        .firstName(employeeRequest.getFirstName())
        .secondName(employeeRequest.getSecondName())
        .firstLastName(employeeRequest.getFirstLastName())
        .secondLastName(employeeRequest.getSecondLastName())
        .email(employeeRequest.getEmail())
        .phoneNumber(employeeRequest.getPhoneNumber())
        .dni(employeeRequest.getDni())
        .build();
        return employee;
    }

    public static EmployeeResponse toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
        .personId(employee.getPersonId())
        .firstName(employee.getFirstName())
        .secondName(employee.getSecondName())
        .firstLastName(employee.getFirstLastName())
        .secondLastName(employee.getSecondLastName())
        .email(employee.getEmail())
        .phoneNumber(employee.getPhoneNumber())
        .dni(employee.getDni())
        .build();
        return employeeResponse;
    }
}
