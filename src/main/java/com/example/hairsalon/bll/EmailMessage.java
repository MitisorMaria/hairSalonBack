package com.example.hairsalon.bll;

import org.springframework.stereotype.Component;

@Component
public class EmailMessage {
    private String address;
    private String body;

    public EmailMessage(String address, String body) {
        this.address = address;
        this.body = body;
    }

    public EmailMessage() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
