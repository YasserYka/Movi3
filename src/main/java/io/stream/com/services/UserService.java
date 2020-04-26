package io.stream.com.services;

import io.stream.com.mappers.UserMapper;
import io.stream.com.models.User;
import io.stream.com.models.dtos.SignUpDto;
import io.stream.com.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public User getCurrentLoggedInUser(){
        return loadUserByUsername(getUsernameFromSecurityContextHolder());
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if(!userOptional.isPresent()){
            log.error("User not found with username {}", username);
            throw new UsernameNotFoundException(username);
        }

        return  userOptional.get();
    }

    public void signup(SignUpDto signUpDto){

        //TODO: Encode password
        repository.save(UserMapper.mapSignUp(signUpDto, ""));
    }

    public void login(){}

    private String getUsernameFromSecurityContextHolder(){ return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(); }
}
