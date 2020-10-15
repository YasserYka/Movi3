package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private Long userId;
    private String username;
    private String email;
    private int avatarId;
    private boolean enabled;
    private String bio;
    private String creationDate;
    private String lastSeen;
    private String fullName;
    
}
