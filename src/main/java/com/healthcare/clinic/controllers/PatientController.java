package com.healthcare.clinic.controllers;

import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatePatientDto;
import com.healthcare.clinic.entities.Patient;
import com.healthcare.clinic.services.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
@Validated
@Tag(
        name = "REST APIs Endpoint for Patients",
        description = "REST APIs Endpoint for Patients")

public class PatientController {

    private IPatientService patientService;

    @Operation(summary = "Create a new Patient REST APi", description = "REST API to create a new Patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Http Status Created"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto) {
        PatientDto createPatient = patientService.createPatient(patientDto);
        return ResponseEntity
                .status(201)
                .body(createPatient);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@Valid @RequestBody UpdatePatientDto patientDto) {
        boolean isUpdated = patientService.updatePatient(patientDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("The Patient has been updated successfully.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("An error occurred during update.");
        }
    }

    @Operation(summary = "Fetch All the patients REST API", description = "REST API to fetch All the Patients and their appointments details")
    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> list = patientService.getAllPatients();
        return new ResponseEntity<List<PatientDto>>(list, HttpStatus.OK);
    }


    @Operation(summary = "Delete patient account", description = "REST API to delete Patient based on National ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "417", description = "Http Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePatient(@RequestParam String nationalID) {
        boolean isDeleted = patientService.deletePatient(nationalID);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Patient DELETED");
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Oops! Something went wrong .");
        }
    }

}
