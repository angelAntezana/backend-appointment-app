package com.backend.appointment.appointment_app.services;

import com.backend.appointment.appointment_app.dto.AppointmentDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;

public interface AppointmentService {
    public AppointmentDto create(AppointmentDto appointmentRequest) throws CustomException;
}
