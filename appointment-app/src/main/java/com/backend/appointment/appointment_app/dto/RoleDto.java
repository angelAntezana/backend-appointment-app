package com.backend.appointment.appointment_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @JsonProperty("roleId")
    private Long roleId;

    @JsonProperty("name")
    private String name;

}
