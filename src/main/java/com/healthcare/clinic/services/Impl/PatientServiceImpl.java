package com.healthcare.clinic.services.Impl;

import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatePatientDto;
import com.healthcare.clinic.entities.Patient;
import com.healthcare.clinic.exception.PatientAlreadyExistsException;
import com.healthcare.clinic.exception.ResourceNotFoundException;
import com.healthcare.clinic.mapper.PatientMapper;
import com.healthcare.clinic.repositories.PatientRepository;
import com.healthcare.clinic.services.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    private PatientRepository patientRepository;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        logger.info("Creating a new patient with National ID: {}", patientDto.getNationalID());
        Patient patient = PatientMapper.mapToPatient(patientDto, new Patient());
        patientRepository.findByNationalID(patient.getNationalID())
                .ifPresent(p -> {
                    logger.error("Failed to create patient: National ID {} already exists", patient.getNationalID());
                    throw new PatientAlreadyExistsException("A Patient with this national ID already exists.");
                });
        Patient savedPatient = patientRepository.save(patient);
        logger.info("Successfully created a patient with ID: {}", savedPatient.getId());

        return PatientMapper.mapToPatientDto(savedPatient,new PatientDto());

    }

    @Override
    @Cacheable(value = "patients")
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(p -> PatientMapper.mapToPatientDto(p, new PatientDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updatePatient(UpdatePatientDto patientDto) {
        boolean isUpdated = false;
        Patient patient = patientRepository.findByNationalID(patientDto.getNationalID())
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Patient with this National ID isn't found.");
                });
        PatientMapper.mapToPatient(patientDto, patient);
        patientRepository.save(patient);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deletePatient(String nationalID) {
        boolean isDeleted = false;
        Patient patient = patientRepository.findByNationalID(nationalID)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with this National ID isn't found."));
        patientRepository.delete(patient);
        isDeleted = true;
        return isDeleted;
    }
}
