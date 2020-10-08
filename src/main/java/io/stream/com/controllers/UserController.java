package io.stream.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.User;
import io.stream.com.models.dtos.ContactFormDto;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.services.EmailService;
import io.stream.com.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/profile")
    public ProfileDto getProfile(){ 
        return userService.getProfile(); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(@RequestBody ProfileDto profileDto, @PathVariable("id") Long id){

        return new ResponseEntity<>(userService.update(profileDto, id), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/contactform")
    public ResponseEntity<?> submitContactForm(@RequestBody ContactFormDto contactFormDto){
        emailService.sendContactForm(contactFormDto);

        return new ResponseEntity<>("Thank you for verifying your email address", HttpStatus.OK);
    }

    @GetMapping("/lastseen")
    public ResponseEntity<?> lastseen(){ 

        userService.updateLastseen();
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}