package com.backend.appointment.appointment_app.exceptions.enums;

public enum EmployeeMessageError {
    EMAIL_ALREADY_EXISTS("EMPLOYEE-0000", "This email %s already exists", 406),
    DNI_ALREADY_EXISTS("EMPLOYEE-0001", "This dni %s already exists", 400),
    EMPLOYEE_NOT_FOUND("EMPLOYEE-0002", "This employee with id %s does not exist", 400);

    private final String code;
    private final String message;
    private final Integer statusCode;

    EmployeeMessageError(String code, String message, Integer statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    public String getCode() {
        return code;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
