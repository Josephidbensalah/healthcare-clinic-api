package com.healthcare.clinic.services;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatedAppointmentDto;
import com.healthcare.clinic.entities.Appointment;
import com.healthcare.clinic.entities.Doctor;
import com.healthcare.clinic.entities.Patient;
import com.healthcare.clinic.exception.ResourceNotFoundException;
import com.healthcare.clinic.repositories.AppointmentRepository;
import com.healthcare.clinic.repositories.DoctorRepository;
import com.healthcare.clinic.repositories.PatientRepository;
import com.healthcare.clinic.services.Impl.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AppointmentServiceTest {

    @Mock private AppointmentRepository appointmentRepository;
    @Mock private PatientRepository patientRepository;
    @Mock private DoctorRepository doctorRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void createAppointment_Success() {
        // Arrange
        PatientDto pDto = new PatientDto(); pDto.setNationalID("P100");
        DoctorDto dDto = new DoctorDto(); dDto.setId(1L);
        AppointmentDto appDto = new AppointmentDto();
        appDto.setPatientDto(pDto);
        appDto.setDoctorDto(dDto);

        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment();

        when(patientRepository.findByNationalID("P100")).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        AppointmentDto result = appointmentService.createAppointment(appDto);

        // Assert
        assertNotNull(result);
        verify(appointmentRepository).save(any());
    }

    @Test
    void updateAppointment_NotFound_ThrowsException() {
        // Arrange
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                appointmentService.updateAppointment(1L, new UpdatedAppointmentDto())
        );
    }
}
