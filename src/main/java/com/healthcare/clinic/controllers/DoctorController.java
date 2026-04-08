package com.healthcare.clinic.controllers;

import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.services.IDoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
@AllArgsConstructor
@Validated
@Tag(
        name = "REST APIs Endpoint for Doctors",
        description = "REST APIs Endpoint for Doctors")
public class DoctorController {

    private IDoctorService doctorService;

    @Operation(summary = "Create a new Doctor REST APi", description = "REST API to create a new Doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Http Status Created"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto createDoctor = doctorService.createDoctor(doctorDto);
        return ResponseEntity
                .status(201)
                .body(createDoctor);
    }
}
