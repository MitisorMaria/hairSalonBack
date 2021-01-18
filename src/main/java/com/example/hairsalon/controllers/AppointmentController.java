package com.example.hairsalon.controllers;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value="appointment")
    private ResponseEntity<?> makeAppointment(@RequestBody Appointment appointment){
        try {
            appointmentService.makeAppointment(appointment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
