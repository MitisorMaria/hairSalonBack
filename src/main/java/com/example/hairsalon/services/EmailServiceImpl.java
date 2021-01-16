package com.example.hairsalon.services;

import com.example.hairsalon.bll.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private EmailSender sender;

    public void sendEmail(String address, String message) throws MessagingException {
        sender.sendEmail(address, message);
    }
}
