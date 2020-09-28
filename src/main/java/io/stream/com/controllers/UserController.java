package io.stream.com.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.User;
import io.stream.com.models.dtos.ContactFormDto;
import io.stream.com.models.dtos.LoginDto;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
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
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpDto signUpDto){

        if(userService.isEmailExists(signUpDto.getEmail()))
            return new ResponseEntity<>("This email already exists", HttpStatus.CONFLICT);
    
        if(userService.isNotMatching(signUpDto.getPassword(), signUpDto.getConfirmedPassword()))
            return new ResponseEntity<>("The confirmed password does not match", HttpStatus.UNPROCESSABLE_ENTITY);

        if(userService.isUsernameExists(signUpDto.getUsername()))
            return new ResponseEntity<>("This Username already exists", HttpStatus.CONFLICT);
        
        return new ResponseEntity<>(userService.signup(signUpDto), HttpStatus.CREATED);    
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(@RequestBody ProfileDto profileDto, @PathVariable("id") Long id){

        return new ResponseEntity<>(userService.update(profileDto, id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam Optional<String> token){
        if(userService.isEmailTokenNotValid(token.get()))
            return new ResponseEntity<>("Token is not valid", HttpStatus.UNAUTHORIZED);
        
        userService.enableAccount(token.get());

        return new ResponseEntity<>("Thank you for verifying your email address", HttpStatus.OK);
    }

    @PostMapping("/contactform")
    public ResponseEntity<?> submitContactForm(@RequestBody ContactFormDto contactFormDto){
        emailService.sendContactForm(contactFormDto);

        return new ResponseEntity<>("Thank you for verifying your email address", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){ 
        
        return new ResponseEntity<>(userService.authenticate(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/lastseen")
    public ResponseEntity<?> lastseen(){ 

        userService.lastseen();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}