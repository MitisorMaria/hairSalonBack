package com.example.hairsalon.services;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

        boolean beforeNow = start.before(new Timestamp(System.currentTimeMillis()));
        Long millisInDayStart = start.getTime() % (24 * 60 * 60 * 1000);
        Long millisInDayEnd = end.getTime() % (24 * 60 * 60 * 1000);
        boolean startsBeforeOpeningTime = millisInDayStart < 9 * 60 * 60 * 1000;
        boolean endsAfterClosingTime = millisInDayEnd > 18 * 60 * 60 * 1000;

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(start);
        boolean onSunday = cal.get(java.util.Calendar.DAY_OF_WEEK) == 1;
        boolean outsideOfficeHours = startsBeforeOpeningTime || endsAfterClosingTime || onSunday;

        for (Appointment a : allAppointments) {
            Timestamp startExisting = a.getTimestamp();
            Timestamp endExisting = new Timestamp(startExisting.getTime() + a.getDurationInMilliseconds());
            boolean beforeExistingOverlap = startExisting.before(end) && start.before(startExisting);
            boolean afterExistingOverlap = start.before(endExisting) && startExisting.before(start);
            boolean insideOverlap = startExisting.before(start) && end.before(endExisting);
            boolean onStartOverlap = startExisting.equals(start);
            boolean onEndOverlap = endExisting.equals(end);
            if (outsideOfficeHours || beforeNow || beforeExistingOverlap || afterExistingOverlap ||
                    onStartOverlap || onEndOverlap || insideOverlap) {
                return false;
            }
        }
        return true;
    }


}
