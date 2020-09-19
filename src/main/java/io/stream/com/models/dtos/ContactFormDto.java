package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormDto {

    private String email;
    private String fullName;
    private String subject;
    private String body;
    
}
