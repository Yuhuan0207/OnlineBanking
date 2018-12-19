package com.userfront.yuhuan.service.UserServiceImpl;

import com.userfront.yuhuan.dao.AppointmentDao;
import com.userfront.yuhuan.domain.Appointment;
import com.userfront.yuhuan.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentDao appointmentDao;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public Optional<Appointment> findAppointment(Long id) { //TODO: the CrudRepository changed. findOne() --> findById()
        return appointmentDao.findById(id);
    }

    public void confirmAppointment(Long id) {
        Appointment appointment = findAppointment(id).get(); //Here, use 'get()' to retrieve the appointment entity.
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }

}
