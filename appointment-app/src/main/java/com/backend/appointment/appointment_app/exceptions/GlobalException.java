package com.backend.appointment.appointment_app.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GlobalException {
    private String reason;
    private Integer status;
    private String code;
    private Map<String, Object> details;
}
