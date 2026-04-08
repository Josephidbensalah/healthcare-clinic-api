package com.healthcare.clinic.services.Impl;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.UpdatedAppointmentDto;
import com.healthcare.clinic.entities.Appointment;
import com.healthcare.clinic.entities.Doctor;
import com.healthcare.clinic.entities.Patient;
import com.healthcare.clinic.exception.ResourceNotFoundException;
import com.healthcare.clinic.mapper.AppointmentMapper;
import com.healthcare.clinic.repositories.AppointmentRepository;
import com.healthcare.clinic.repositories.DoctorRepository;
import com.healthcare.clinic.repositories.PatientRepository;
import com.healthcare.clinic.services.IAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NotificationService notificationService;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        // check if Patient exists :
        Patient patient = patientRepository.findByNationalID(appointmentDto.getPatientDto().getNationalID())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // check if Doctor exists :
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, new Appointment());

        // map patient and doctor
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Calling an Async metho to simulate sending email to patient after appointment :
        notificationService.sendAppointmentNotification(
                savedAppointment.getPatient().getEmail(),
                savedAppointment.getPatient().getFullNameEnglish()
        );


        return AppointmentMapper.mapToAppointmentDto(savedAppointment,new AppointmentDto());
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(ap -> AppointmentMapper.mapToAppointmentDto(ap, new AppointmentDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAppointment(Long id, UpdatedAppointmentDto appointmentDto) {
        boolean isUpdated = false;
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with this ID isn't found."));

        AppointmentMapper.mapToAppointment(appointmentDto, appointment);

        // update patient if National ID has changed
        if(appointmentDto.getPatientDto() != null &&
                !appointment.getPatient().getNationalID().equals(appointmentDto.getPatientDto().getNationalID())) {
            Patient newPatient = patientRepository.findByNationalID(appointmentDto.getPatientDto().getNationalID())
                    .orElseThrow(() -> new ResourceNotFoundException("The Patient National ID provided is not found."));
            appointment.setPatient(newPatient);
        }

        // update doctor if ID has changed
        if(appointmentDto.getDoctorDto() != null &&
                !appointment.getDoctor().getId().equals(appointmentDto.getDoctorDto().getId())) {
            Doctor newDoctor = doctorRepository.findById(appointmentDto.getDoctorDto().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("The Doctor ID provided is not found."));
            appointment.setDoctor(newDoctor);
        }

        appointmentRepository.save(appointment);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteAppointment(Long id) {
        boolean isDeleted = false;
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with this ID isn't found."));
        appointmentRepository.delete(appointment);
        isDeleted = true;
        return isDeleted;
    }
}
