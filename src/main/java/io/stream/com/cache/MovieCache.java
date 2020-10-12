package io.stream.com.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovieCache {

    private static final String VIEWING_HISTORY_KEY = "viewing:history:movieId";

    private static final String UNIQUE_VIEWS_KEY = "unique:views:movieId:ip";

    // fixed number of movies to return to frontend 
    private static final int LIST_SIZE = 6;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // This will be used by the scheduled task that reduce unique views
    public void pushToViewingHistory(Long movieId){

        redisTemplate.opsForList().leftPush(VIEWING_HISTORY_KEY, movieId);
    }
 
    public List<Integer> getUniqueViews(){

        return (List<Integer>) (List) redisTemplate.opsForList().range(UNIQUE_VIEWS_KEY, 0, -1); 
    }

    // Store movieId and IP of the user to persist unique viewing count in scheduled task
    public void pushToUniqueViews(Long movieId, String ip){

        redisTemplate.opsForHash().putIfAbsent(UNIQUE_VIEWS_KEY, ip + movieId, movieId); 
    }

    // Gets movies recntly pushed in viewing history
    public List<Integer> getRecentlyWatchedMovies(){

        return (List<Integer>) (List) redisTemplate.opsForList().range(UNIQUE_VIEWS_KEY, 0, LIST_SIZE); 
    }

}
