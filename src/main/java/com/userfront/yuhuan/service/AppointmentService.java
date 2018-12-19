package com.userfront.yuhuan.service;

import com.userfront.yuhuan.domain.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);

    List<Appointment> findAll();

    Optional<Appointment> findAppointment(Long id);

    void confirmAppointment(Long id);
}
