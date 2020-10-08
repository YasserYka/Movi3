package io.stream.com.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailedLoginAttempt {
    
    private int failedAttemptsCount;

    // Next attempt allowed time in milliseconds after exceeding maximum number of failed attempts
    private Long nextAttemptAllowed;

    // To clean unused keys in redis 
    private Long lastAttempt;

}
