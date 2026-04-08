package com.healthcare.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "Doctor", description = "Schema to hold the Doctor information")
public class DoctorDto {

    private Long id;

    @Schema(description = "Full Name of the Doctor", example = "Mohammed Ahmed")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the Doctor name should be between 5 and 30")
    private String nameEnglish;

    @Schema(description = "Name of the Doctor", example = "محمد أحمد")
    @NotEmpty(message = "Name (Arabic) cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the Doctor name(Arabic) should be between 5 and 30")
    private String nameArabic;

    @Schema(description = "Speciality  of the Doctor", example = "Neurology")
    @NotEmpty(message = "The Doctor speciality can't be empty or null")
    private String speciality;

    @PositiveOrZero(message = "the number of years of experience should be equal or greater than zero")
    @Schema(description = "The years of experience of a doctor", example = "3")
    private short yearsOfExperience;

    @Positive(message = "the consultation duration should be greater than zero")
    @Schema(description = "The consultation duration of a doctor(in minutes)", example = "30")
    private short consultationDuration;

    @Schema(description = "list of Appointments Details associated with the Doctor")
    private List<AppointmentDto> appointmentDtos;
}
