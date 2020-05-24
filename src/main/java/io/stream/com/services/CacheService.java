package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.list.beingwatched.key}")
	private String beingwatchedListKey;

    @Value("${redis.hash.recentviews.key}")
    private String recentviewsHashKey;
    
    @Value("${redis.hash.emailverification.key}")
    private String emailVerificationHashKey;

    public void leftPushMovieBeingWatched(Movie movie){ 
        redisTemplate.opsForList().leftPush(beingwatchedListKey, movie); 
    }

    public List<Movie> getListOfMoviesBeingWatched(){
         return (List<Movie>) (List) redisTemplate.opsForList().range(beingwatchedListKey, 0, -1); 
    }

    public void rightPopMovieBeingWatched(){
         redisTemplate.opsForList().rightPop(beingwatchedListKey); 
    }

    public void leftPushAllMoviesBeingWatched(List<Movie> movies){
         redisTemplate.opsForList().leftPushAll(beingwatchedListKey, movies); 
    }

    public Long getSizeMoviesBeingWatched() {
         return redisTemplate.opsForList().size(beingwatchedListKey); 
    }

    public void putRecentViewedMovie(String ip, Long movieId){
         redisTemplate.opsForHash().putIfAbsent(recentviewsHashKey, ip + movieId, movieId); 
    }

    public List<Integer> getRecentViewdMoviesValues() { 
        return (List<Integer>) (List) redisTemplate.opsForHash().values(recentviewsHashKey); 
    }
    
    //TODO: Add expiration date
    public void addEmailVerifyingToken(String token, String email){ 
        redisTemplate.opsForHash().put(emailVerificationHashKey, token, email);
    }

    //TODO: DELETE EXPIRED KEYS

    public String getEmailOfToken(String token){
        return (String) redisTemplate.opsForHash().get(emailVerificationHashKey, token);
    }

    //TODO: Check expiration date
    public boolean isExistAndValidEmailToken(String token){
        return redisTemplate.opsForHash().hasKey(emailVerificationHashKey, token);
    }

    public List<String> getall(){
        return (List<String>) (List) redisTemplate.opsForHash().values(emailVerificationHashKey);
    } 

}