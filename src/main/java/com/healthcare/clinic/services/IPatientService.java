package com.healthcare.clinic.services;

import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatePatientDto;


import java.util.List;


public interface IPatientService {

    PatientDto createPatient(PatientDto patientDto);

    List<PatientDto> getAllPatients();

    boolean updatePatient(UpdatePatientDto patientDto);

    boolean deletePatient(String nationalID);
}
