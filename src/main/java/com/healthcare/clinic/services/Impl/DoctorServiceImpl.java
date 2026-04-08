package com.healthcare.clinic.services.Impl;

import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.entities.Doctor;
import com.healthcare.clinic.exception.ResourceNotFoundException;
import com.healthcare.clinic.mapper.DoctorMapper;
import com.healthcare.clinic.repositories.DoctorRepository;
import com.healthcare.clinic.services.IDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    private DoctorRepository doctorRepository;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto, new Doctor());
        Doctor savedDoctor = doctorRepository.save(doctor);
        DoctorDto savedDTO = DoctorMapper.mapToDoctorDto(savedDoctor,new DoctorDto());

        return savedDTO;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(d -> DoctorMapper.mapToDoctorDto(d, new DoctorDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateDoctor(DoctorDto doctorDto) {
        boolean isUpdated = false;
        Doctor doctor = doctorRepository.findById(doctorDto.getId())
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Doctor with this ID isn't found.");
                });
        DoctorMapper.mapToDoctor(doctorDto, doctor);
        doctorRepository.save(doctor);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteDoctor(Long id) {
        boolean isDeleted = false;
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with this ID isn't found."));
        doctorRepository.delete(doctor);
        isDeleted = true;
        return isDeleted;
    }
}
