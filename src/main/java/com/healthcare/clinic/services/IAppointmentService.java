package com.healthcare.clinic.services;

import com.healthcare.clinic.dto.AppointmentDto;
import com.healthcare.clinic.dto.UpdatedAppointmentDto;

import java.util.List;

public interface IAppointmentService {

    AppointmentDto createAppointment(AppointmentDto AppointmentDto);

    List<AppointmentDto> getAllAppointments();

    boolean updateAppointment(Long id, UpdatedAppointmentDto AppointmentDto);

    boolean deleteAppointment(Long id);
}
