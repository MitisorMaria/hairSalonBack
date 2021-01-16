package com.example.hairsalon.controllers;

import com.example.hairsalon.bll.EmailMessage;
import com.example.hairsalon.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping(value="/email")
    private String Hellomail() {
        return "This works";
    }

    @PostMapping(value="email")
    private ResponseEntity<?> sendEmail(@RequestBody EmailMessage message) {
        try {
            String messageBody = message.getBody();
            String address = message.getAddress();
            emailService.sendEmail(address, messageBody);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
