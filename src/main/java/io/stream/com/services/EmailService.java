package io.stream.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.stream.com.models.User;
import io.stream.com.utils.KeyUtil;

@Service
public class EmailService { 

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CacheService cacheService;

    /*public void send(Email email){ mailSender.send(EmailMapper.map(email)); }*/

    public void verifyEmail(User user){
        
        String token = KeyUtil.generate();

        //TODO: Put token as key in redis with expiration date and id as value

        //TODO: Send email with link of /verify/token
    }

}