package io.stream.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.stream.com.cache.EmailCache;
import io.stream.com.mappers.UserMapper;
import io.stream.com.models.User;
import io.stream.com.models.dtos.AuthenticationDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.security.JWTService;
import io.stream.com.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailCache emailCache;

    public User getCurrentLoggedInUser(){ 

        return userService.loadUserByUsername(getUsernameFromSecurityContextHolder()); 
    }

    // Check if Email validation token is not expired 
    public boolean isEmailTokenNotValid(String token){ 
        
        return !emailCache.isValidToken(token); 
    }

    // Signup and send verfication email 
    public AuthenticationDto signup(SignUpDto signUpDto) {
        String token = KeyUtil.generate();

        userService.save(UserMapper.mapSignUp(signUpDto, passwordEncoder.encode(signUpDto.getPassword())));
        
        //emailService.sendVerification(signUpDto.getEmail(), token);

        //cacheService.addEmailVerifyingToken(token, signUpDto.getEmail());

        return authenticate(signUpDto.getUsername(), signUpDto.getPassword());
    }
    
    public AuthenticationDto authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        return new AuthenticationDto(jwtService.generateToken((User) authenticate.getPrincipal()));
    }

    private String getUsernameFromSecurityContextHolder() { 
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((User)principal).getUsername();
        else
            return principal.toString();
    }
}
