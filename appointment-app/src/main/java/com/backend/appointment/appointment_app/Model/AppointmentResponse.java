package com.backend.appointment.appointment_app.Model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class AppointmentResponse {
    private Long appointmentId;
    private Long customeId;
    private Long employeeId;
    private Date initDate;
    private Integer duration;
}
