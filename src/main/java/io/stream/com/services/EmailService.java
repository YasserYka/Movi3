package io.stream.com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService { 

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage message){
        mailSender.send(message);
    }
}