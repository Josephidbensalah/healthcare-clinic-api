package com.healthcare.clinic.services;

import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.entities.Patient;
import com.healthcare.clinic.exception.PatientAlreadyExistsException;
import com.healthcare.clinic.repositories.PatientRepository;
import com.healthcare.clinic.services.Impl.PatientServiceImpl;
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
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void createPatient_Success() {
        // Arrange
        PatientDto dto = new PatientDto();
        dto.setNationalID("N123");
        Patient patient = new Patient();
        patient.setNationalID("N123");

        when(patientRepository.findByNationalID("N123")).thenReturn(Optional.empty());
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Act
        PatientDto result = patientService.createPatient(dto);

        // Assert
        assertNotNull(result);
        verify(patientRepository, times(1)).save(any());
    }

    @Test
    void createPatient_ThrowsException_WhenExists() {
        PatientDto dto = new PatientDto();
        dto.setNationalID("N123");

        when(patientRepository.findByNationalID("N123")).thenReturn(Optional.of(new Patient()));

        assertThrows(PatientAlreadyExistsException.class, () -> patientService.createPatient(dto));
    }
}