package com.healthcare.clinic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SoftDelete(columnName = "is_deleted")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 50)
    @NotBlank(message = "Name is required")
    private String nameEnglish;

    @Size(min = 5, max = 50)
    @NotBlank(message = "Name(Arabic) is required")
    private String nameArabic;

    @NotEmpty(message = "Speciality is required")
    private String speciality;

    @PositiveOrZero @Max(100)
    @NotNull(message = "Years of Experience is required")
    private short yearsOfExperience;

    @Positive
    @NotNull(message = "Consultation duration (in minutes) is required ")
    private short consultationDuration;

    @OneToMany()
    private List<Appointment> appointments;
}
