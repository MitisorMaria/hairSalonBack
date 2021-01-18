package com.example.hairsalon.bll;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Component
@Entity
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(name="timestamp")
    private Timestamp timestamp;
    @Column(name="message")
    private String message;
    @Column(name="service")
    private Service service;

    public Appointment() {
    }

    public Appointment(String message, Timestamp timestamp, Service service) {
        this.message = message;
        this.timestamp = timestamp;
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getDurationInMilliseconds() {
        switch (this.service) {
            case HAIRCUT_WOMEN:
            case DYE_WOMEN:
                return 2*60*60*1000;
            case STYLE_MEN:
                return 20*60*1000;
            case STYLE_WOMEN:
                return 30*60*1000;
            case HAIRCUT_MEN:
            case DYE_MEN:
            default:
                return 60*60*1000;
        }
    }
}
