package com.backend.appointment.appointment_app.services.impl;

import org.springframework.stereotype.Service;

import com.backend.appointment.appointment_app.dto.AppointmentDto;
import com.backend.appointment.appointment_app.entity.Appointment;
import com.backend.appointment.appointment_app.entity.Customer;
import com.backend.appointment.appointment_app.entity.Employee;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.exceptions.enums.CustomerMessageError;
import com.backend.appointment.appointment_app.exceptions.enums.EmployeeMessageError;
import com.backend.appointment.appointment_app.repository.AppointmentRepository;
import com.backend.appointment.appointment_app.repository.CustomerRepository;
import com.backend.appointment.appointment_app.repository.EmployeeRepository;
import com.backend.appointment.appointment_app.services.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final EmployeeRepository employeeRepository;

    private final CustomerRepository customerRepository;

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDto create(AppointmentDto appointmentRequest) throws CustomException {
         Employee employee = employeeRepository.findById(appointmentRequest.getEmployeeId())
                .orElseThrow(() -> new CustomException(
                    EmployeeMessageError.EMPLOYEE_NOT_FOUND.getCode(),
                    EmployeeMessageError.EMPLOYEE_NOT_FOUND.getMessage(appointmentRequest.getEmployeeId()),
                    EmployeeMessageError.EMPLOYEE_NOT_FOUND.getStatusCode()
                ));

        Customer customer = customerRepository.findById(appointmentRequest.getCustomerId())
                .orElseThrow(() -> new CustomException(
                    CustomerMessageError.CUSTOMER_NOT_FOUND.getCode(),
                    CustomerMessageError.CUSTOMER_NOT_FOUND.getMessage(appointmentRequest.getCustomerId()),
                    CustomerMessageError.CUSTOMER_NOT_FOUND.getStatusCode()
                ));

           // Validar conflictos de citas para el cliente
           int customerConflicts = appointmentRepository.countCustomerAppointmentsInInterval(appointmentRequest.getCustomerId(), appointmentRequest.getInitDate(), appointmentRequest.getEndDate());
           if (customerConflicts > 0) {
            throw new CustomException("APPOINTMENT", "There is no availability in this time slot.", 403);
    }
   
           // Validar conflictos de citas para el empleado
           int employeeConflicts = appointmentRepository.countEmployeeAppointmentsInInterval(appointmentRequest.getEmployeeId(), appointmentRequest.getInitDate(), appointmentRequest.getEndDate());
           if (employeeConflicts > 0) {
            throw new CustomException("APPOINTMENT", "There is no availability in this time slot.", 403);
    }
        Appointment newAppointment = Appointment.builder()
        .initDate(appointmentRequest.getInitDate())
        .endDate(appointmentRequest.getEndDate())
        .duration(appointmentRequest.getDuration())
        .employee(employee)
        .customer(customer)
        .build();

        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        return AppointmentDto.builder()
        .appointmentId(savedAppointment.getAppointmentId())
        .initDate(savedAppointment.getInitDate())
        .endDate(savedAppointment.getEndDate())
        .duration(savedAppointment.getDuration())
        .employeeId(savedAppointment.getEmployee().getPersonId())
        .customerId(savedAppointment.getCustomer().getPersonId())
        .build();
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
