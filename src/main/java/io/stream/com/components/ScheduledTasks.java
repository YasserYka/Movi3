package io.stream.com.components;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.stream.com.cache.AuthCache;
import io.stream.com.cache.MovieCache;
import io.stream.com.services.MovieService;
import io.stream.com.utils.PopularityUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {

    private final static int TWO_HOURS = 200000;

    private final static int THIRTY_MINUTES = 300000;

    @Autowired
    private MovieCache movieCache;

    @Autowired
    private AuthCache authCache;

    @Autowired
    private MovieService movieService;

    // User's view count is kept in the cache to prevent duplicate user views and prevent spamming, this clear the cache and persist the views
    @Scheduled(fixedDelay=THIRTY_MINUTES)
    public void updateViewCounts(){
        log.info("SCHEDULED TASK: Updating view count");

        HashMap<Integer, Integer> reduce = new HashMap<Integer, Integer>();

        movieCache.getUniqueViews().forEach(id -> {  reduce.put(id, reduce.getOrDefault(id, 1)); });

        reduce.entrySet().forEach(entry -> { movieService.updateViewCount(Long.valueOf(entry.getKey()), entry.getValue()); });
    }

    @Scheduled(fixedDelay=TWO_HOURS)
    public void updatePopularityScore(){
        log.info("SCHEDULED TASK: Updating popularity score");

        movieService.getAll().forEach(movie -> {
            movieService.updatePopularityScore(movie.getMovieId(), PopularityUtil.calculateScore(movie.getLikeCount(), movie.getUploadDate()));
        });
    }

    // The redis keys are not deleted after successful login or lock time finishes because redis does not support TTL for values in hash
    @Scheduled(fixedDelay=TWO_HOURS)
    public void deleteUnusedFailedLoginAttemptsKeys(){
        log.info("SCHEDULED TASK: Removing unsed keys for failed login attempts record");

        authCache.deleteUnusedOrExpiredKeys();
    }
}