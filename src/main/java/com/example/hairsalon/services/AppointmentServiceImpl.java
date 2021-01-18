package com.example.hairsalon.services;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Timestamp;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void makeAppointment (Appointment appointment) throws Exception{
        if (validateAppointment(appointment)) {
            appointmentRepository.save(appointment);
        } else {
            throw new Exception ("Appointment cannot be made. Select another time.");
        }
    }

    public boolean validateAppointment(Appointment appointment) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        Timestamp start = new Timestamp(appointment.getDate().getTime() + appointment.getTime().getTime());
        Timestamp end = new Timestamp(start.getTime() + appointment.getDurationInMilliseconds());
        for (Appointment a : allAppointments) {
            Timestamp startExisting = new Timestamp(a.getDate().getTime() + a.getTime().getTime());
            Timestamp endExisting = new Timestamp(start.getTime() + a.getDurationInMilliseconds());
            boolean beforeExistingOverlap = startExisting.before(end) && start.before(endExisting);
            boolean afterExistingOverlap = startExisting.before(end) && end.before(endExisting);
            if(beforeExistingOverlap || afterExistingOverlap || onStartOverlap || onEndOverlap){
                return false;
            }
        }
        return true;
    }


}
