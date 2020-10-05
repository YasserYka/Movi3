package io.stream.com.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class AuthCache {

    private static final String FAILED_LOGIN_ATTEMPTS_KEY = "failed:login:attempts:username";

    private static final int MAXIMUM_FAILED_LOGIN_ATTEMPTS = 5;

    // fixed number of movies to return to frontend 
    private static final int LIST_SIZE = 6;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Keep record of failed login attempts 
    public void failedLoginAttempt(String username){

        redisTemplate.opsForHash().increment(FAILED_LOGIN_ATTEMPTS_KEY, username, 1);
    }

    // Check if not failed login attempts before or not exceeded maximum attempts
    public boolean isAllowedToLogin(String username){

        if (!redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username))
            return true;

        return (Integer) redisTemplate.opsForHash().get(FAILED_LOGIN_ATTEMPTS_KEY, username) == MAXIMUM_FAILED_LOGIN_ATTEMPTS ? false : true;
    }

    // Rest login attempts after successful login
    public void resetLoginAttempts(String username){
        redisTemplate.opsForHash().put(FAILED_LOGIN_ATTEMPTS_KEY, username, 0);
    }

}
