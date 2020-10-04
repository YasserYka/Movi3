package io.stream.com.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class EmailCache {
    
    private static final String EMAIL_VERIFICATION_TOKEN_KEY = "viewing:history:movie";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String getToken(String token){

        return (String) redisTemplate.opsForHash().get(EMAIL_VERIFICATION_TOKEN_KEY, token);
    }

    public void addToken(String token, String email){ 

        redisTemplate.opsForHash().put(EMAIL_VERIFICATION_TOKEN_KEY, token, email);
    }

    public boolean isValidToken(String token){

        return redisTemplate.opsForHash().hasKey(EMAIL_VERIFICATION_TOKEN_KEY, token);
    }

    public String getEmailOf(String token){
        
        return (String) redisTemplate.opsForHash().get(EMAIL_VERIFICATION_TOKEN_KEY, token);
    }

}
