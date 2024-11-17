package com.backend.appointment.appointment_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponse {
     private Long personId;

    private String firstName;

    private String secondName;

    private String firstLastName;

    private String secondLastName;

    private String email;

    private String phoneNumber;
}
