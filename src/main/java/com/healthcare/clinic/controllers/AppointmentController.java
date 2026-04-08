package com.healthcare.clinic.controllers;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.ErrorResponseDto;
import com.healthcare.clinic.dto.UpdatedAppointmentDto;
import com.healthcare.clinic.services.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
@Validated
@Tag(
        name = "REST APIs Endpoint for Scheduling an Appointment",
        description = "REST APIs Endpoint for Scheduling an Appointment for a patient and within a selected doctor")
public class AppointmentController {

    private IAppointmentService appointmentService;

    @Operation(
            summary = "Schedule Appointment REST API",
            description = "REST API to Schedule Appointment"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/schedule")
    public ResponseEntity<AppointmentDto> scheduleAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody UpdatedAppointmentDto appointmentDto) {
        boolean isUpdated = false;

        // Call the service to handle fetching and partial mapping
        isUpdated = appointmentService.updateAppointment(id, appointmentDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("The Appointment has been updated successfully.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("An error occurred during update.");
        }
    }

}
