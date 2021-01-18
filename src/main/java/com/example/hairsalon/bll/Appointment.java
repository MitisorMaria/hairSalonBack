package com.example.hairsalon.bll;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Component
@Entity
@Table(name="appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column(name="date")
    private Date date;
    @Column(name="message")
    private String message;
    @Column(name="time")
    private Time time;
    @Column(name="service")
    private Service service;

    public Appointment() {
    }

    public Appointment(Date date, String message, Time time, Service service) {
        this.date = date;
        this.message = message;
        this.time = time;
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
