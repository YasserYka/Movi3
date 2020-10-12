package io.stream.com.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.cache.AuthCache;
import io.stream.com.models.dtos.LoginDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.services.AuthService;
import io.stream.com.services.UserService;
import io.stream.com.utils.TimeUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthCache authCache;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpDto signUpDto){

        if(userService.isEmailExists(signUpDto.getEmail()))
            return new ResponseEntity<>("This email already exists", HttpStatus.CONFLICT);
    
        if(!signUpDto.getPassword().equals(signUpDto.getConfirmedPassword()))
            return new ResponseEntity<>("The confirmed password does not match", HttpStatus.UNPROCESSABLE_ENTITY);

        if(userService.isUsernameExists(signUpDto.getUsername()))
            return new ResponseEntity<>("This Username already exists", HttpStatus.CONFLICT);
        
        return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);    
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam Optional<String> token){

        if(authService.isEmailTokenNotValid(token.get()))
            return new ResponseEntity<>("Token is not valid", HttpStatus.UNAUTHORIZED);
        
        userService.enableAccount(token.get());

        return new ResponseEntity<>("Thank you for verifying your email address", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response){ 

        if (authCache.isNotAllowedToLoginAttempt(loginDto.getUsername())){
            response.setHeader("Retry-After", authCache.retryAfter(loginDto.getUsername()));
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        
        return new ResponseEntity<>(authService.authenticate(loginDto.getUsername(), loginDto.getPassword()), HttpStatus.OK);
    }
}
