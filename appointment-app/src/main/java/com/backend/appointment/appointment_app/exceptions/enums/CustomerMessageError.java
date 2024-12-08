package com.backend.appointment.appointment_app.exceptions.enums;

public enum CustomerMessageError {
    CUSTOMER_NOT_FOUND("CUSTOMER-0002", "This customer with id %s does not exist", 400),
    EMAIL_ALREADY_EXISTS("CUSTOMER-0000", "This email %s already exists", 406);

    private final String code;
    private final String message;
    private final Integer statusCode;

    CustomerMessageError(String code, String message,Integer statusCode) {
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
