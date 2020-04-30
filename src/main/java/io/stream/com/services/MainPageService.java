package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
public class MainPageService {

	@Autowired
	private RedisService redisService;

    @Value("${redis.list.beingwatchedrightnow}")
	private String redisListNameOfMoviesBeingWatched;

	private static final int NUMBER_OF_MOVIES_BEING_WATCHED = 6;
	
	public List<Movie> get6MoviesBeingWatched() { return redisService.getListOfMovies(redisListNameOfMoviesBeingWatched); }

    public void addToMoviesBeingWatched(Movie movie){
        if(redisService.getSizeOf(redisListNameOfMoviesBeingWatched) == NUMBER_OF_MOVIES_BEING_WATCHED)
			redisService.rightPopMovieOf(redisListNameOfMoviesBeingWatched);
		redisService.leftPushMovieOf(redisListNameOfMoviesBeingWatched, movie);
    }

}
