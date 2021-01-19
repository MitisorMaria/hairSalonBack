package com.example.hairsalon.services;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("employee", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Appointment> example = Example.of(new Appointment(null, null, null, appointment.getEmployee()), customExampleMatcher);

        List<Appointment> allAppointments = appointmentRepository.findAll(example);
        LocalDateTime start = appointment.getTimestamp();
        LocalDateTime end = start.plusMinutes(appointment.getDuration());

        boolean beforeNow = start.isBefore(LocalDateTime.now());
        boolean startsBeforeOpeningTime = start.getHour() < 9;
        boolean endsAfterClosingTime = end.getHour() > 18;
        boolean startsAfterClosingTime = start.getHour() >= 18;

        boolean onSunday = start.getDayOfWeek().getValue() == 7;
        boolean outsideOfficeHours = startsBeforeOpeningTime || startsAfterClosingTime || endsAfterClosingTime || onSunday;

        for (Appointment a : allAppointments) {
            LocalDateTime startExisting = a.getTimestamp();
            LocalDateTime endExisting = startExisting.plusMinutes(a.getDuration());

            boolean beforeExistingOverlap = startExisting.isBefore(end) && start.isBefore(startExisting);
            boolean afterExistingOverlap = start.isBefore(endExisting) && startExisting.isBefore(start);

            boolean insideOverlap = startExisting.isBefore(start) && end.isBefore(endExisting);
            boolean onStartOverlap = startExisting.equals(start);
            boolean onEndOverlap = endExisting.equals(end);

            if (beforeExistingOverlap || afterExistingOverlap ||
                    onStartOverlap || onEndOverlap || insideOverlap) {
                return false;
            }
        }
        if (outsideOfficeHours || beforeNow )
            return false;
        return true;
    }


}
