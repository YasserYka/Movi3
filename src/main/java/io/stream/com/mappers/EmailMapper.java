package io.stream.com.mappers;

import org.springframework.mail.SimpleMailMessage;

import io.stream.com.models.Email;

public class EmailMapper {

    public static SimpleMailMessage map(Email email){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getMessage());
        simpleMailMessage.setFrom(email.getFrom());
        
        return simpleMailMessage;
    }
    
}