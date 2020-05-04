package io.stream.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.stream.com.mappers.EmailMapper;
import io.stream.com.models.Email;

@Service
public class EmailService { 

    @Autowired
    private JavaMailSender mailSender;

    public void send(Email email){ mailSender.send(EmailMapper.map(email)); }

}