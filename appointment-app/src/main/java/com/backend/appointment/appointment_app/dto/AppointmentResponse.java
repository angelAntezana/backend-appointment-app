package com.backend.appointment.appointment_app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class AppointmentResponse {
    private Long appointmentId;
    private Long customerId;
    private Long employeeId;
    private Date initDate;
    private Integer duration;
}
