package com.backend.appointment.appointment_app.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionManager {
    

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException e, WebRequest request) {
        return setInformation(e, e.getCode(), request, e.getStatusCode());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        return setInformationValidation(ex, "VALIDATION-JAKARTA", request, 400);
    }


    private ResponseEntity<GlobalException> setInformationValidation(MethodArgumentNotValidException e, String code, WebRequest request, Integer status) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        // Obtener HttpServletRequest desde WebRequest
        HttpServletRequest httpRequest = ((ServletWebRequest) request).getRequest();
        // Obtener el método del servicio desde el stack trace
        String serviceMethod = getServiceMethodName(e);
        Map<String, Object> details = new HashMap<>();
        details.put("path", httpRequest.getRequestURI());
        details.put("method", httpRequest.getMethod());
        details.put("serviceMethod", serviceMethod); 
        details.put("sessionId", request.getSessionId());
        return ResponseEntity.status(status)
            .body(
                GlobalException.builder()
                .reason(String.join("\n", errors))
                .code(code)
                .status(status)
                .details(details)
                .build()
            );
    }

    private ResponseEntity<GlobalException> setInformation(Exception e, String code, WebRequest request, Integer status) {
            // Obtener HttpServletRequest desde WebRequest
            HttpServletRequest httpRequest = ((ServletWebRequest) request).getRequest();
            // Obtener el método del servicio desde el stack trace
            String serviceMethod = getServiceMethodName(e);
            Map<String, Object> details = new HashMap<>();
            details.put("path", httpRequest.getRequestURI()); // Path del endpoint solicitado
            details.put("method", httpRequest.getMethod());  // Método HTTP (GET, POST, etc.)
            details.put("serviceMethod", serviceMethod); 
            details.put("sessionId", request.getSessionId());
    return ResponseEntity.status(status)
        .body(
            GlobalException.builder()
            .reason(e.getMessage())
            .code(code)
            .status(status)
            .details(details)
            .build()
        );
    }


    // Método para obtener el nombre del método del servicio que generó el error
    private String getServiceMethodName(Throwable throwable) {
        for (StackTraceElement element : throwable.getStackTrace()) {
            if (element.getClassName().startsWith("com.backend.appointment.appointment_app.services.impl")) {
                return element.getMethodName(); // Devuelve el nombre del método
            }
        }
        // Por defecto si no se encuentra ya que es un método de 
        // biblioteca externa
        return "Unknown Method";
    }
}
