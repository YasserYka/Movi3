package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    
    private String username;
    private String password;
    private String email;

}