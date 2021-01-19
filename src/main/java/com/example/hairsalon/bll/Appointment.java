package com.example.hairsalon.bll;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(name="timestamp")
    private LocalDateTime timestamp;
    @Column(name="message")
    private String message;
    @Column(name="service")
    private Service service;
    @Column(name="employee")
    private Employee employee;

    public Appointment() {
    }

    public Appointment(String message, LocalDateTime timestamp, Service service, Employee employee) {
        this.message = message;
        this.timestamp = timestamp;
        this.service = service;
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getDuration() {
        switch (this.service) {
            case HAIRCUT_WOMEN:
            case DYE_WOMEN:
                return 2*60;
            case STYLE_MEN:
                return 20;
            case STYLE_WOMEN:
                return 30;
            case HAIRCUT_MEN:
            case DYE_MEN:
            default:
                return 60;
        }
    }
}
