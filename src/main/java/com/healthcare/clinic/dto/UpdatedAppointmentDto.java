package com.healthcare.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Appointment", description = "Schema to hold the Appointment information for a patient")
public class UpdatedAppointmentDto {

    private Long id;

    @Schema(description = "Patient Details", implementation = PatientDto.class)
    private PatientDto patientDto;

    @Schema(description = "Doctor Details", implementation = DoctorDto.class)
    private DoctorDto doctorDto;

    @Schema(description = "Appointment date and time", example = "2026-04-10T10:00:00")
    private LocalDateTime appointmentTime;

    @Schema(description = "Status of the appointment", allowableValues = {"SCHEDULED", "COMPLETED", "CANCELLED"})
    private String status;

    @Schema(description = "notes of the appointment",maxProperties = 250)
    private String notes;

}
