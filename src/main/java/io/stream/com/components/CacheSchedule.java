package io.stream.com.components;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.stream.com.models.Movie;
import io.stream.com.services.CacheService;
import io.stream.com.services.MovieService;

@Component
public class CacheSchedule {

    private final static int TWO_HOURS = 200000;

    private final static int THIRTY_MINUTES = 30000;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MovieService MovieService;

    @Scheduled(fixedDelay=THIRTY_MINUTES)
    public void updateViewCounts(){
        List<Movie> movies = cacheService.getRecentViewdMoviesValues();

        movies.forEach(movie -> {
            
        });
    }

    @Scheduled(fixedDelay=TWO_HOURS)
    public void updateTrendingMovies(){
        List<Movie> movies = MovieService.getAll();

    }

    public double calculatePopularityScore(int numberOfLikes, Date creationDate){
        return (numberOfLikes - 1) / Math.pow(dateToHours(creationDate) + 2, 1.5);
    }

    public int dateToHours(Date creationDate){
        return 0;
    }

}