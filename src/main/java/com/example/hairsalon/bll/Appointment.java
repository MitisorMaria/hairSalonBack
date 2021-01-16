package com.example.hairsalon.bll;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private Date date;
    private String message;
    private Time time;

    public Appointment() {
    }

    public Appointment(Date date, String message, Time time) {
        this.date = date;
        this.message = message;
        this.time = time;
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


}
