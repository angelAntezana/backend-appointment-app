package com.backend.appointment.appointment_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {

    @JsonProperty("personId")
    private Long personId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("firstLastName")
    private String firstLastName;

    @JsonProperty("secondLastName")
    private String secondLastName;

    @JsonProperty("email")
    @Email( message = "Email is not valid",regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("sendNotificationEmail")
    private boolean sendNotificationEmail;

    @JsonProperty("sendNotificationPhoneNumber")
    private boolean sendNotificationPhoneNumber;
}
