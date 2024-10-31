package com.backend.appointment.appointment_app.Model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AppointmentRequest {
    private Long customeId;
    private Long employeeId;
    private Date initDate;
    private Integer duration;
}
