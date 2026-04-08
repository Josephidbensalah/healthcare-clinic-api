package com.healthcare.clinic.services;



import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.entities.Doctor;
import com.healthcare.clinic.repositories.DoctorRepository;
import com.healthcare.clinic.services.Impl.DoctorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class DoctorServiceImplTest {

    @Mock private DoctorRepository doctorRepository;
    @InjectMocks private DoctorServiceImpl doctorService;

    @Test
    void createDoctor_Success() {
        DoctorDto dto = new DoctorDto();
        Doctor doctor = new Doctor();

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        DoctorDto result = doctorService.createDoctor(dto);

        assertNotNull(result);
        verify(doctorRepository).save(any());
    }
}