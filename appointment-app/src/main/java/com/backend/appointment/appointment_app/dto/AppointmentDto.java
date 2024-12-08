package com.backend.appointment.appointment_app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentDto {
    private Long appointmentId;
    private Long customerId;
    private Long employeeId;
    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private Integer duration;
}
