package com.backend.appointment.appointment_app.services;

import java.time.LocalDateTime;

public interface AppointmentService {
    void addAppointment(LocalDateTime initDate) throws Exception;
}
