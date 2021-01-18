package com.example.hairsalon.services;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Timestamp start = appointment.getTimestamp();
        Timestamp end = new Timestamp(start.getTime() + appointment.getDurationInMilliseconds());
        for (Appointment a : allAppointments) {
            Timestamp startExisting = a.getTimestamp();
            Timestamp endExisting = new Timestamp(startExisting.getTime() + a.getDurationInMilliseconds());
            boolean beforeExistingOverlap = startExisting.before(end) && start.before(startExisting);
            boolean afterExistingOverlap = start.before(endExisting) && startExisting.before(start);
            boolean insideOverlap = startExisting.before(start) && end.before(endExisting);
            boolean onStartOverlap = startExisting.equals(start);
            boolean onEndOverlap = endExisting.equals(end);
            boolean beforeNow = start.before(new Timestamp(System.currentTimeMillis()));
            if(beforeNow || beforeExistingOverlap || afterExistingOverlap || onStartOverlap || onEndOverlap || insideOverlap){
                return false;
            }
        }
        return true;
    }


}
