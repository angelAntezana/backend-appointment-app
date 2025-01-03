package com.backend.appointment.appointment_app.mapper;

import java.util.ArrayList;
import java.util.List;

import com.backend.appointment.appointment_app.dto.EmployeeDto;
import com.backend.appointment.appointment_app.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
        throw new IllegalStateException("Mapper employee class");
    }
    public static Employee toEntity(EmployeeDto employeeRequest) {
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
        // WHEN A EMPLOYEE IS CREATED
        if (employeeRequest.getPersonId() == null) {
            return employee;
        }
        // UPDATE EMPLOYEE
        employee.setPersonId(employeeRequest.getPersonId());
        return employee;
    }

    public static EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeDto.builder()
        .personId(employee.getPersonId())
        .firstName(employee.getFirstName())
        .secondName(employee.getSecondName())
        .firstLastName(employee.getFirstLastName())
        .secondLastName(employee.getSecondLastName())
        .email(employee.getEmail())
        .phoneNumber(employee.getPhoneNumber())
        .dni(employee.getDni())
        .build();
    }

    public static List<EmployeeDto> toDtoList(List<Employee> listEmployee) {
        List<EmployeeDto> listEmployeeDtos = new ArrayList<>();

        for (Employee employee: listEmployee) {
            listEmployeeDtos.add(toDto(employee));
        }
        return listEmployeeDtos;
    }
}
