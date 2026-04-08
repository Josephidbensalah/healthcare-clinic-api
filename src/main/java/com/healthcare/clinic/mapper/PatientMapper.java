package com.healthcare.clinic.mapper;

import com.healthcare.clinic.dto.AddressDto;
import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatePatientDto;
import com.healthcare.clinic.entities.Address;
import com.healthcare.clinic.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientDto mapToPatientDto(Patient patient, PatientDto patientDto) {
        if (patient == null) {
            return null;
        }

        patientDto.setId(patient.getId());
        patientDto.setFullNameEnglish(patient.getFullNameEnglish());
        patientDto.setFullNameArabic(patient.getFullNameArabic());
        patientDto.setEmail(patient.getEmail());
        patientDto.setMobileNumber(patient.getMobileNumber());
        patientDto.setBirthDate(patient.getBirthDate());
        patientDto.setNationalID(patient.getNationalID());

        if (patient.getAddress() != null) {
            patientDto.setAddressDto(AddressMapper.mapToAddressDto(patient.getAddress(), new AddressDto()));
        }

        // Map List of Appointments
        if (patient.getAppointments() != null && !patient.getAppointments().isEmpty()) {
            List<AppointmentDto> appointmentDtos = patient.getAppointments().stream()
                    .map(appointment -> {
                        return AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDto());
                    })
                    .collect(Collectors.toList());

            patientDto.setAppointmentDtos(appointmentDtos);
        } else {
            patientDto.setAppointmentDtos(new ArrayList<>());
        }

        return patientDto;
    }

    public static Patient mapToPatient(PatientDto patientDto, Patient patient) {
        if (patientDto == null) {
            return null;
        }
        if (patientDto.getId() != null) {
            patient.setId(patientDto.getId());
        }

        if (patientDto.getFullNameEnglish() != null) {
            patient.setFullNameEnglish(patientDto.getFullNameEnglish());
        }
        if (patientDto.getFullNameArabic() != null) {
            patient.setFullNameArabic(patientDto.getFullNameArabic());
        }
        if (patientDto.getEmail() != null) {
            patient.setEmail(patientDto.getEmail());
        }
        if (patientDto.getMobileNumber() != null) {
            patient.setMobileNumber(patientDto.getMobileNumber());
        }
        if (patientDto.getBirthDate() != null) {
            patient.setBirthDate(patientDto.getBirthDate());
        }
        if (patientDto.getNationalID() != null) {
            patient.setNationalID(patientDto.getNationalID());
        }

        if (patientDto.getAddressDto() != null) {
            if (patient.getAddress() == null) {
                patient.setAddress(new Address());
            }
            patient.setAddress(AddressMapper.mapToAddress(patientDto.getAddressDto(), new Address()));
        }

        return patient;
    }


    public static Patient mapToPatient(UpdatePatientDto patientDto,Patient patient){
        if (patientDto == null) {
            return null;
        }
        if (patientDto.getId() != null) {
            patient.setId(patientDto.getId());
        }

        if (patientDto.getFullNameEnglish() != null) {
            patient.setFullNameEnglish(patientDto.getFullNameEnglish());
        }
        if (patientDto.getFullNameArabic() != null) {
            patient.setFullNameArabic(patientDto.getFullNameArabic());
        }
        if (patientDto.getEmail() != null) {
            patient.setEmail(patientDto.getEmail());
        }
        if (patientDto.getMobileNumber() != null) {
            patient.setMobileNumber(patientDto.getMobileNumber());
        }
        if (patientDto.getBirthDate() != null) {
            patient.setBirthDate(patientDto.getBirthDate());
        }
        if (patientDto.getNationalID() != null) {
            patient.setNationalID(patientDto.getNationalID());
        }

        if (patientDto.getAddressDto() != null) {
            if (patient.getAddress() == null) {
                patient.setAddress(new Address());
            }
            patient.setAddress(AddressMapper.mapToAddress(patientDto.getAddressDto(), new Address()));
        }

        return patient;
    }


}
