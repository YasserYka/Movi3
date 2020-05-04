package io.stream.com.services;

import io.stream.com.mappers.UserMapper;
import io.stream.com.models.User;
import io.stream.com.models.dtos.AuthenticationDto;
import io.stream.com.models.dtos.LoginDto;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.repositories.UserRepository;
import io.stream.com.securities.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    public User getCurrentLoggedInUser(){ return loadUserByUsername(getUsernameFromSecurityContextHolder()); }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if(!userOptional.isPresent()){
            log.error("User not found with username {}", username);
            throw new UsernameNotFoundException(username);
        }

        return  userOptional.get();
    }

    public AuthenticationDto authenticate(LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        return new AuthenticationDto(jwtService.generateToken((User) authenticate.getPrincipal()));
    }

    public void signup(SignUpDto signUpDto){
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        repository.save(UserMapper.mapSignUp(signUpDto));
    }

    private String getUsernameFromSecurityContextHolder(){ return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(); }

    public ProfileDto profile(){ return UserMapper.mapProfile(getCurrentLoggedInUser()); }
}
