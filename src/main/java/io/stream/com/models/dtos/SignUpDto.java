package io.stream.com.models.dtos;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {

    @NotEmpty
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;

}
