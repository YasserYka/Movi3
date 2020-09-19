package io.stream.com.utils;

import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {

    private static final String ORGANIZATION_NAME = "DIDN'TTHINKABOUTNAME";

    private static final String VERIFYING_SUBJECT = "Verify Your Email Address";

    private static final String VERIFYING_MESSAGE = "Click the blew link to verify your email address";

    private static final String VERIFYING_ENDPOINT = "http://localhost:8080/api/v1/users/verify?token=";

    public static SimpleMailMessage createVerifyingEmail(String toEmail, String token){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(VERIFYING_SUBJECT);
        simpleMailMessage.setText(VERIFYING_MESSAGE + '\n' + VERIFYING_ENDPOINT + token);
        simpleMailMessage.setFrom(ORGANIZATION_NAME);
        
        return simpleMailMessage;
    }

    public static SimpleMailMessage createContactForm(String subject, String fullname, String body, String fromEmail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(ORGANIZATION_NAME);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText("From: " + fullname + '\n' + body);
        simpleMailMessage.setFrom(fromEmail);
        
        return simpleMailMessage;
    }
    
}