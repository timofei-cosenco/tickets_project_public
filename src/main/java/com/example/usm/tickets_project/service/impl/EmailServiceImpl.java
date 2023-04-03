package com.example.usm.tickets_project.service.impl;

import com.example.usm.tickets_project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleEmail(List<String> adresses){
        for(String address: adresses) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(address);
            simpleMailMessage.setSubject("itTicket :)");
            simpleMailMessage.setText("You have new tasks! :)");
            emailSender.send(simpleMailMessage);
        }
    }
}
