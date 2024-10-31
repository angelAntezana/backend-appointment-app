package com.backend.appointment.appointment_app.services;

import java.time.LocalDateTime;

public interface AppointmentService {
    void crearCita(LocalDateTime inicio) throws Exception;
}
