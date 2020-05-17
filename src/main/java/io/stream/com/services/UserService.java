package io.stream.com.services;

import io.stream.com.mappers.UserMapper;
import io.stream.com.models.User;
import io.stream.com.models.dtos.AuthenticationDto;
import io.stream.com.models.dtos.LoginDto;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.repositories.UserRepository;
import io.stream.com.securities.JWTService;
import io.stream.com.utils.KeyUtil;
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

import com.amazonaws.client.builder.AdvancedConfig.Key;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private CacheService cacheService;

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

    public boolean isNotMatching(String password, String confirmedPassword){ return !password.equals(confirmedPassword); }

    public AuthenticationDto authenticate(LoginDto loginDto) {

        System.out.println(repository.findByUsername(loginDto.getUsername().toString()));

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        return new AuthenticationDto(jwtService.generateToken((User) authenticate.getPrincipal()));
    }

    public void signup(SignUpDto signUpDto) {
        String token = KeyUtil.generate();

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        repository.save(UserMapper.mapSignUp(signUpDto));

        emailService.verifyEmail(signUpDto.getEmail(), token);

        cacheService.addEmailVerifyingToken(token, signUpDto.getEmail());
    }

    public boolean isEmailTokenValid(String token){ 
        return cacheService.isExistAndValidEmailToken(token); 
    }

    public void enableAccount(String token){
        cacheService.
    }

    private String getUsernameFromSecurityContextHolder() { 
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(); 
    }

    public ProfileDto profile() { 
        return UserMapper.mapProfile(getCurrentLoggedInUser());
    }

    public boolean emailExists(String email) { 
        return repository.existsByEmail(email); 
    }
}
