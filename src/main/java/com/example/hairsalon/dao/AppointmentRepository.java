package com.example.hairsalon.dao;

import com.example.hairsalon.bll.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {

}
