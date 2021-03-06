package io.stream.com.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailTokenTTL {
    
    private String email;

    // creation time in milliseconds
    private Long generatedTime;

}
