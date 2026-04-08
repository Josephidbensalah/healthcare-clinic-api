package com.healthcare.clinic.dto;

import com.healthcare.clinic.entities.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(name = "Appointment", description = "Schema to hold the Appointment information for a patient")
public class AppointmentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "Patient Details", implementation = PatientDto.class)
    @NotNull(message = "The Patient can't be empty or null")
    private PatientDto patientDto;

    @Schema(description = "Doctor Details", implementation = DoctorDto.class)
    @NotNull(message = "The Doctor can't be empty or null")
    private DoctorDto doctorDto;

    @Schema(description = "Appointment date and time", example = "2026-04-10T10:00:00")
    @NotNull(message = "The Date time can't be empty or null")
    private LocalDateTime appointmentTime;

    @Schema(description = "Status of the appointment", allowableValues = {"SCHEDULED", "COMPLETED", "CANCELLED"})
    private String status;

    @Schema(description = "notes of the appointment",maxProperties = 250)
    private String notes;

}
