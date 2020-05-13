package io.stream.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.dtos.AuthenticationDto;
import io.stream.com.models.dtos.LoginDto;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ProfileDto getProfile(){ return userService.profile(); }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto){

        if(userService.emailExists(signUpDto.getEmail()))
            return new ResponseEntity<>("This email already exists", HttpStatus.CONFLICT);
    
        if(userService.isNotMatching(signUpDto.getPassword(), signUpDto.getConfirmedPassword()))
            return new ResponseEntity<>("The confirm password does not match", HttpStatus.NOT_ACCEPTABLE);

        userService.signup(signUpDto);

        return new ResponseEntity<>(HttpStatus.CREATED);    
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody LoginDto loginDto){ return new ResponseEntity<>(userService.authenticate(loginDto), HttpStatus.OK); }
}