package com.healthcare.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "UpdatePatientDTO", description = "Schema to hold the Patient information")
public class UpdatePatientDto {

    private Long id;

    @Schema(description = "Full Name of the Patient", example = "Mohammed Ahmed")
    @Size(min = 5, max = 30, message = "The length of the patient full name should be between 5 and 30")
    private String fullNameEnglish;

    @Schema(description = "Full Name of the Patient", example = "محمد أحمد")
    @Size(min = 5, max = 30, message = "The length of the patient full name(Arabic) should be between 5 and 30")
    private String fullNameArabic;

    @Schema(description = "Email of the Patient", example = "mohammed.ahmed@mail.com")
    @Email(message = "The email Address should have a valid value")
    private String email;


    @Schema(description = "Mobile Number of the Patient", example = "9876543210")
    private String mobileNumber;

    @Schema(description = "list of Appointments Details of the Patient")
    private List<AppointmentDto> appointmentDtos;

    @Schema(description = "Patient National ID or CIVIL ID", example = "NI654654")
    private String nationalID;

    @Schema(description = "The Patient Birth Date", format = "date", example = "2024-03-27")
    private LocalDate birthDate;

    @Schema(description = "The Patient Address",implementation = AddressDto.class)
    private AddressDto addressDto;

}
