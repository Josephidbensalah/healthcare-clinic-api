package com.healthcare.clinic.mapper;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.dto.UpdatedDoctorDto;
import com.healthcare.clinic.entities.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    // Map Entity to DTO
    public static DoctorDto mapToDoctorDto(Doctor doctor, DoctorDto doctorDto) {
        if (doctor == null) return null;

        doctorDto.setId(doctor.getId());
        doctorDto.setNameEnglish(doctor.getNameEnglish());
        doctorDto.setNameArabic(doctor.getNameArabic());
        doctorDto.setSpeciality(doctor.getSpeciality());
        doctorDto.setYearsOfExperience(doctor.getYearsOfExperience());
        doctorDto.setConsultationDuration(doctor.getConsultationDuration());

        // map the Appointments list
        if (doctor.getAppointments() != null && !doctor.getAppointments().isEmpty()) {
            List<AppointmentDto> appointmentDtos = doctor.getAppointments().stream()
                    .map(appointment -> {
                        // Important: Pass a new DTO instance for each appointment
                        return AppointmentMapper.mapToAppointmentDto(appointment, new AppointmentDto());
                    })
                    .collect(Collectors.toList());

            doctorDto.setAppointmentDtos(appointmentDtos);
        } else {
            doctorDto.setAppointmentDtos(new ArrayList<>());
        }

        return doctorDto;
    }

    // Map DTO to Entity
    public static Doctor mapToDoctor(DoctorDto doctorDto, Doctor doctor) {
        if (doctorDto == null) return null;

        if (doctorDto.getId() != null) {
            doctor.setId(doctorDto.getId());
        }

        doctor.setNameEnglish(doctorDto.getNameEnglish());
        doctor.setNameArabic(doctorDto.getNameArabic());
        doctor.setSpeciality(doctorDto.getSpeciality());
        doctor.setYearsOfExperience(doctorDto.getYearsOfExperience());
        doctor.setConsultationDuration(doctorDto.getConsultationDuration());

        return doctor;
    }

    public static Doctor mapToDoctor(UpdatedDoctorDto doctorDto, Doctor doctor) {
        if (doctorDto == null) return null;

        if (doctorDto.getId() != null) {
            doctor.setId(doctorDto.getId());
        }

        if (doctorDto.getNameEnglish() != null) {
            doctor.setNameEnglish(doctorDto.getNameEnglish());
        }
        if (doctorDto.getNameArabic() != null) {
            doctor.setNameArabic(doctorDto.getNameArabic());
        }
        if (doctorDto.getSpeciality() != null) {
            doctor.setSpeciality(doctorDto.getSpeciality());
        }

        if (doctorDto.getYearsOfExperience() > 0) {
            doctor.setYearsOfExperience(doctorDto.getYearsOfExperience());
        }
        if (doctorDto.getConsultationDuration() > 0) {
            doctor.setConsultationDuration(doctorDto.getConsultationDuration());
        }

        return doctor;
    }

}
