package com.backend.appointment.appointment_app.services.impl;

import java.time.LocalDateTime;

import com.backend.appointment.appointment_app.services.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService{

    @Override
    public void addAppointment(LocalDateTime initDate) throws Exception {
        // TODO
        System.out.println(initDate);
        throw new UnsupportedOperationException("Unimplemented method 'crearCita'");
    }


    // public Cita crearCita(Cita nuevaCita) throws Exception {
    //     LocalDateTime inicio = nuevaCita.getInicio();
    //     LocalDateTime fin = inicio.plusMinutes(nuevaCita.getDuracion());
    //     Long empleadoId = nuevaCita.getEmpleado().getId();

    //     // Verificar si ya hay 3 citas en el intervalo de la hora seleccionada
    //     int citasEnIntervalo = citaRepository.contarCitasEnIntervalo(inicio, fin);

    //     if (citasEnIntervalo >= 3) {
    //         throw new Exception("Ya hay 3 citas en este intervalo de tiempo.");
    //     }

    //     // Verificar si el mismo empleado ya tiene una cita en la misma franja horaria
    //     boolean empleadoTieneCita = citaRepository.existeCitaParaEmpleadoEnIntervalo(empleadoId, inicio, fin);

    //     if (empleadoTieneCita) {
    //         throw new Exception("El empleado ya tiene una cita en este intervalo de tiempo.");
    //     }

    //     // Guardar la cita si no hay conflicto
    //     return citaRepository.save(nuevaCita);
    // }


    // REPOSITORY

 // Contar citas en un intervalo de 15 minutos
//  @Query("SELECT COUNT(c) FROM Cita c WHERE c.inicio BETWEEN :inicio AND :fin")
//  int contarCitasEnIntervalo(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

//     @Query("SELECT COUNT(c) > 0 FROM Cita c WHERE c.empleado.id = :empleadoId AND c.inicio BETWEEN :inicio AND :fin")
// boolean existeCitaParaEmpleadoEnIntervalo(@Param("empleadoId") Long empleadoId, @Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
    
}
