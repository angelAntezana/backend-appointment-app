package com.backend.appointment.appointment_app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class CustomException extends Exception{
    private final String code;
    private final String message;
    private final int statusCode;
}
