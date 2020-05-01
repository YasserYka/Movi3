package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Movie> moviesTemplate;

    public void leftPushMovieOf(String key, Movie movie){ moviesTemplate.opsForList().leftPush(key, movie); }

    public List<Movie> getListOfMovies(String key){ return moviesTemplate.opsForList().range(key, 0, -1); }

    public Long getSizeOf(String key) { return moviesTemplate.opsForList().size(key); }

    public void rightPopMovieOf(String key){ moviesTemplate.opsForList().rightPop(key); }
}