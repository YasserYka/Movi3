package io.stream.com.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.stream.com.models.Email;

@Service
public class EmailService { 

    private JavaMailSender mailSender;

    private void send(Email email){ }

}