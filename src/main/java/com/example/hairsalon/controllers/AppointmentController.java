package com.example.hairsalon.controllers;

import com.example.hairsalon.bll.Appointment;
import com.example.hairsalon.services.AppointmentService;
import com.example.hairsalon.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl appointmentService;

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
