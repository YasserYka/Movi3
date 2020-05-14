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
    private RedisTemplate<String, Object> moviesTemplate;

    @Value("${redis.list.beingwatched}")
	private String beingwatchedListKey;

    public void leftPushMovieBeingWatched(Movie movie){ moviesTemplate.opsForList().leftPush(beingwatchedListKey, movie); }
    public List<Movie> getListOfMoviesBeingWatched(){ return (List<Movie>) (List) moviesTemplate.opsForList().range(beingwatchedListKey, 0, -1); }
    public void rightPopMovieBeingWatched(){ moviesTemplate.opsForList().rightPop(beingwatchedListKey); }
    public void leftPushAllMoviesBeingWatched(List<Movie> movies){ moviesTemplate.opsForList().leftPushAll(beingwatchedListKey, movies); }
    public Long getSizeMoviesBeingWatched() { return moviesTemplate.opsForList().size(beingwatchedListKey); }

}