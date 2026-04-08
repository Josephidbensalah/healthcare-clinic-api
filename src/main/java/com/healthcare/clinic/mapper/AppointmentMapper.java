package com.healthcare.clinic.mapper;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.DoctorDto;
import com.healthcare.clinic.dto.PatientDto;
import com.healthcare.clinic.dto.UpdatedAppointmentDto;
import com.healthcare.clinic.entities.Appointment;

public class AppointmentMapper {

    // Map Entity to DTO
    public static AppointmentDto mapToAppointmentDto(Appointment appointment, AppointmentDto appointmentDto) {
        if (appointment == null) return null;

        appointmentDto.setId(appointment.getId());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setNotes(appointment.getNotes());

        // mapping the patient
        if (appointment.getPatient() != null) {
            appointmentDto.setPatientDto(PatientMapper.mapToPatientDto(appointment.getPatient(), new PatientDto()));
        }

        // mapping the doctor
        if (appointment.getDoctor() != null) {
            appointmentDto.setDoctorDto(DoctorMapper.mapToDoctorDto(appointment.getDoctor(), new DoctorDto()));
        }

        return appointmentDto;
    }

    // Map DTO to Entity
    public static Appointment mapToAppointment(AppointmentDto appointmentDto, Appointment appointment) {
        if (appointmentDto == null) return null;

        appointment.setId(appointmentDto.getId());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setNotes(appointmentDto.getNotes());

        return appointment;
    }

    public static Appointment mapToAppointment(UpdatedAppointmentDto appointmentDto, Appointment appointment) {
        if (appointmentDto == null) return null;
        if (appointmentDto.getId() != null) {
            appointment.setId(appointmentDto.getId());
        }

        if (appointmentDto.getStatus() != null) {
            appointment.setStatus(appointmentDto.getStatus());
        }

        if (appointmentDto.getAppointmentTime() != null) {
            appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        }

        if (appointmentDto.getNotes() != null) {
            appointment.setNotes(appointmentDto.getNotes());
        }

        return appointment;
    }
}
