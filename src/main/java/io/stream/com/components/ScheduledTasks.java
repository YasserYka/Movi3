package io.stream.com.components;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.stream.com.services.CacheService;
import io.stream.com.services.MovieService;
import io.stream.com.utils.PopularityUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {

    private final static int TWO_HOURS = 200000;

    private final static int THIRTY_MINUTES = 30000;

    private final static int ONE_MINUTE = 10000;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MovieService movieService;

    @Scheduled(fixedDelay=ONE_MINUTE)
    public void updateViewCounts(){
        log.info("SCHEDULED TASK: UPDATING VIEW COUNT");

        HashMap<Integer, Integer> reduce = new HashMap<Integer, Integer>();

        cacheService.getRecentViewdMoviesValues().forEach(id -> { 
            reduce.put(id, reduce.getOrDefault(id, 1));
        });

        reduce.entrySet().forEach(entry -> { movieService.updateViewCount(Long.valueOf(entry.getKey()), entry.getValue()); });
    }

    @Scheduled(fixedDelay=TWO_HOURS)
    public void updatePopularityScore(){
        log.info("SCHEDULED TASK: UPDATING POPULARITY SCORE");

        movieService.getAll().forEach(movie -> {
            movieService.updatePopularityScore(movie.getMovieId(), PopularityUtil.calculateScore(movie.getLikeCount(), movie.getUploadDate()));
        });
    }
}