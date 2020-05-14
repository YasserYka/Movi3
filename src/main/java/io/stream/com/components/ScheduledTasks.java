package io.stream.com.components;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.stream.com.models.Movie;
import io.stream.com.services.CacheService;
import io.stream.com.services.MovieService;
import io.stream.com.utils.PopularityUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {

    private final static int TWO_HOURS = 200000;

    private final static int THIRTY_MINUTES = 30000;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MovieService movieService;

    @Scheduled(fixedDelay=THIRTY_MINUTES)
    public void updateViewCounts(){

        HashMap<Long, Integer> reduce = new HashMap<Long, Integer>();

        cacheService.getRecentViewdMoviesValues().forEach(movie -> { reduce.merge(movie.getMovieId(), 1, Integer::sum); });

        reduce.entrySet().forEach(entry -> { movieService.updateViewCount(entry.getKey(), entry.getValue()); });
    }

    @Scheduled(fixedDelay=TWO_HOURS)
    public void updatePopularityScore(){
        movieService.getAll().forEach(movie -> {
            movieService.updatePopularityScore(movie.getMovieId(), PopularityUtil.calculateScore(movie.getLikeCount(), movie.getUploadDate()));
        });
    }
}