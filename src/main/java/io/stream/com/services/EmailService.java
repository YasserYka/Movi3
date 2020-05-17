package io.stream.com.services;

import java.util.HashMap;

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

    private HashMap<String, String> temp = new HashMap<String, String>();

    /*public void send(Email email){ mailSender.send(EmailMapper.map(email)); }*/

    public void verifyEmail(String userEmail, String token){
        temp.putIfAbsent(token, userEmail);

        //TODO: Put token as key in redis with expiration date and id as value
    }

    public String getUserIdIfExist(String token) {
        return temp.getOrDefault(token, null);
    }

}