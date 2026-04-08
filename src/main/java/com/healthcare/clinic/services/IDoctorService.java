package com.healthcare.clinic.services;


import com.healthcare.clinic.dto.DoctorDto;

import java.util.List;


public interface IDoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto);

    List<DoctorDto> getAllDoctors();

    boolean updateDoctor(DoctorDto doctorDto);

    boolean deleteDoctor(Long id);
}
