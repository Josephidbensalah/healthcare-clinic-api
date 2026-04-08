package com.healthcare.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "Patient", description = "Schema to hold the Patient information")
public class PatientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID cannot be null for update")
    private Long id;

    @Schema(description = "Full Name of the Patient", example = "Mohammed Ahmed")
    @NotEmpty(message = "full Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the patient full name should be between 5 and 30")
    private String fullNameEnglish;

    @Schema(description = "Full Name of the Patient", example = "محمد أحمد")
    @Size(min = 5, max = 30, message = "The length of the patient full name(Arabic) should be between 5 and 30")
    private String fullNameArabic;

    @Schema(description = "Email of the Patient", example = "mohammed.ahmed@mail.com")
    @NotEmpty(message = "The email shouldn't be empty or null")
    @Email(message = "The email Address should have a valid value")
    private String email;


    @Schema(description = "Mobile Number of the Patient", example = "9876543210")
    @NotEmpty(message = "The mobile Number shouldn't be empty or null")
    private String mobileNumber;

    @Schema(description = "list of Appointments Details of the Patient")
    private List<AppointmentDto> appointmentDtos;

    @Schema(description = "Patient National ID or CIVIL ID", example = "NI654654")
    @NotEmpty(message = "The National ID/Civil ID can't be empty or null")
    private String nationalID;

    @Schema(description = "The Patient Birth Date", format = "date", example = "2024-03-27")
    private LocalDate birthDate;

    @Schema(description = "The Patient Address",implementation = AddressDto.class)
    private AddressDto addressDto;

}
