package io.stream.com.mappers;

import io.stream.com.models.User;
import io.stream.com.models.dtos.SignUpDto;

public class UserMapper {

    public static User mapSignUp(SignUpDto signUpDto, String password){
        return User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .accountNonExpired(true)
                .accountNotLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .password(password)
                .build();
    }
}
