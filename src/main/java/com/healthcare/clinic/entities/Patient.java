package com.healthcare.clinic.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SoftDelete(columnName = "is_deleted")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50)
    @NotBlank(message = "Full Name is required")
    private String fullNameEnglish;

    @NotEmpty(message = "full Name (Arabic) cannot be null or empty")
    @Size(min = 5)
    private String fullNameArabic;

    @Email()
    @Column(unique = true)
    @NotEmpty(message = "Email is required")
    private String email;

    private String mobileNumber;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotEmpty(message = "National ID or Civil ID is required")
    @Column(unique = true)
    private String nationalID;

    @Embedded
    @NotNull
    @Valid
    private Address address;

    @OneToMany()
    private List<Appointment> appointments;

}
