package io.stream.com.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.FailedLoginAttempt;
import io.stream.com.utils.TimeUtil;

@Service
public class AuthCache {

    private static final String FAILED_LOGIN_ATTEMPTS_KEY = "failed:login:attempts:username";

    private static final int MAXIMUM_FAILED_LOGIN_ATTEMPTS = 5;

    // 30 minutes in milliseconds
    private static final int LOCK_TIME = 1800000;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Keep record of failed login attempts 
    public void failedLoginAttempt(String username){

        int previousFailedLoginAttemptCount = 0;
        Long backoffTime = 0L;

        if(redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username))
            previousFailedLoginAttemptCount = getLoginAttemptsCount(username);

        if(previousFailedLoginAttemptCount + 1 >= MAXIMUM_FAILED_LOGIN_ATTEMPTS)
            backoffTime = TimeUtil.currentTimeInMillisecondsAfter(LOCK_TIME);
            
        redisTemplate.opsForHash().put(FAILED_LOGIN_ATTEMPTS_KEY, username, new FailedLoginAttempt(previousFailedLoginAttemptCount + 1, backoffTime, System.currentTimeMillis()));
    }

    // Check if not failed login attempts before or not exceeded maximum attempts
    public boolean isNotAllowedToLoginAttempt(String username){

        if (redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username) && getLoginAttemptsCount(username) >= MAXIMUM_FAILED_LOGIN_ATTEMPTS){
            
            if(getNextAttemptAllowed(username) > System.currentTimeMillis())
                return true;
            
            deleteLoginAttemptsRecord(username);
        }

        return false;
    }

    // return Retry-After's header value in minutes
    public String retryAfter(String username){

        return String.valueOf(TimeUtil.millisecondsToMinutes(getNextAttemptAllowed(username) - System.currentTimeMillis()));
    }

    public void deleteLoginAttemptsRecord(String username){

        redisTemplate.opsForHash().delete(FAILED_LOGIN_ATTEMPTS_KEY, username);
    }

    private Long getNextAttemptAllowed(String username){
        
        return (getFailedLoginAttemptObject(username)).getNextAttemptAllowed();
    }

    private int getLoginAttemptsCount(String username){

        return (getFailedLoginAttemptObject(username)).getFailedAttemptsCount();
    }

    private FailedLoginAttempt getFailedLoginAttemptObject(String username){

        return (FailedLoginAttempt) redisTemplate.opsForHash().get(FAILED_LOGIN_ATTEMPTS_KEY, username);
    }

    public void deleteUnusedOrExpiredKeys(){

        redisTemplate.opsForHash().keys(FAILED_LOGIN_ATTEMPTS_KEY).forEach(hashKey -> {

            FailedLoginAttempt failedLoginAttempt = getFailedLoginAttemptObject((String) hashKey);
            
            // If last attempt is old or is allowed to attempt login after the lock 
            if(failedLoginAttempt.getLastAttempt() + LOCK_TIME < System.currentTimeMillis())
                redisTemplate.opsForHash().delete(FAILED_LOGIN_ATTEMPTS_KEY, hashKey);

        });

    }

}
