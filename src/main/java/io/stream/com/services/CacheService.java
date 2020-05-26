package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import lombok.Getter;
import lombok.Setter;

@Setter
@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
@ConfigurationProperties(prefix="redistemplatekey")
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

	private String beingwatched;
    private String recentviews;
    private String emailverification;

    public void leftPushMovieBeingWatched(Movie movie){ 
        redisTemplate.opsForList().leftPush(beingwatched, movie); 
    }

    public List<Movie> getListOfMoviesBeingWatched(){
         return (List<Movie>) (List) redisTemplate.opsForList().range(beingwatched, 0, -1); 
    }

    public void rightPopMovieBeingWatched(){
         redisTemplate.opsForList().rightPop(beingwatched); 
    }

    public void leftPushAllMoviesBeingWatched(List<Movie> movies){
         redisTemplate.opsForList().leftPushAll(beingwatched, movies); 
    }

    public Long getSizeMoviesBeingWatched() {
         return redisTemplate.opsForList().size(beingwatched); 
    }

    public void putRecentViewedMovie(String ip, Long movieId){
         redisTemplate.opsForHash().putIfAbsent(recentviews, ip + movieId, movieId); 
    }

    public List<Integer> getRecentViewdMoviesValues() { 
        return (List<Integer>) (List) redisTemplate.opsForHash().values(recentviews); 
    }
    
    //TODO: Add expiration date
    public void addEmailVerifyingToken(String token, String email){ 
        redisTemplate.opsForHash().put(emailverification, token, email);
    }

    //TODO: DELETE EXPIRED KEYS

    public String getEmailOfToken(String token){
        return (String) redisTemplate.opsForHash().get(emailverification, token);
    }

    //TODO: Check expiration date
    public boolean isExistAndValidEmailToken(String token){
        return redisTemplate.opsForHash().hasKey(emailverification, token);
    }

    public List<String> getall(){
        return (List<String>) (List) redisTemplate.opsForHash().values(emailverification);
    } 

}