package io.stream.com.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.FailedLoginAttempt;
import io.stream.com.utils.TimeUtil;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class AuthCache {

    private static final String FAILED_LOGIN_ATTEMPTS_KEY = "failed:login:attempts:username";

    private static final int MAXIMUM_FAILED_LOGIN_ATTEMPTS = 5;

    // 30 minutes in milliseconds
    private static final int LOCK_TIME = 1800000;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Keep record of failed login attempts 
    public void failedLoginAttempt(String username){

        int previousFailedLoginAttemptCount = 1;
        Long backoffTime = 0L;

        if(redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username))
            previousFailedLoginAttemptCount = getLoginAttemptsCount(username);

            System.out.println(username);
            System.out.println("prev" + redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username));

        if(previousFailedLoginAttemptCount >= MAXIMUM_FAILED_LOGIN_ATTEMPTS)
            backoffTime = TimeUtil.currentTimeInMillisecondsAfter(LOCK_TIME);
            
        redisTemplate.opsForHash().put(FAILED_LOGIN_ATTEMPTS_KEY, username, new FailedLoginAttempt(previousFailedLoginAttemptCount + 1, backoffTime, System.currentTimeMillis()));
    }

    // Check if not failed login attempts before or not exceeded maximum attempts
    public Long isAllowedToLoginAttempt(String username){

        if (!redisTemplate.opsForHash().hasKey(FAILED_LOGIN_ATTEMPTS_KEY, username) || getLoginAttemptsCount(username) <= MAXIMUM_FAILED_LOGIN_ATTEMPTS)
            return 0L;

        return getNextAttemptAllowed(username) - System.currentTimeMillis();
    }

    // Rest login attempts after successful login
    public void resetLoginAttempts(String username){

        redisTemplate.opsForHash().delete(FAILED_LOGIN_ATTEMPTS_KEY, username);
    }

    private Long getNextAttemptAllowed(String username){
        
        return ((FailedLoginAttempt) redisTemplate.opsForHash().get(FAILED_LOGIN_ATTEMPTS_KEY, username)).getNextAttemptAllowed();
    }

    private int getLoginAttemptsCount(String username){

        return ((FailedLoginAttempt) redisTemplate.opsForHash().get(FAILED_LOGIN_ATTEMPTS_KEY, username)).getFailedAttemptsCount();
    }

    public void deleteUnusedOrExpiredKeys(){

        redisTemplate.opsForHash().keys(FAILED_LOGIN_ATTEMPTS_KEY).forEach(hashKey -> {

            FailedLoginAttempt failedLoginAttempt = (FailedLoginAttempt) redisTemplate.opsForHash().get(FAILED_LOGIN_ATTEMPTS_KEY, hashKey);
            
            // If last attempt is old or is allowed to attempt login after the lock 
            if(failedLoginAttempt.getNextAttemptAllowed() < System.currentTimeMillis() || failedLoginAttempt.getLastAttempt() + LOCK_TIME < System.currentTimeMillis())
                redisTemplate.opsForHash().delete(FAILED_LOGIN_ATTEMPTS_KEY, hashKey);

        });

    }

}
