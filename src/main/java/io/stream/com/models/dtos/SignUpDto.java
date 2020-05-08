package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {

    private String username;
    private String email;
    private String password;
    private String confirmedPassword;

}
