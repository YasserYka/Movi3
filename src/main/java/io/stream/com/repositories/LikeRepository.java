package io.stream.com.repositories;

import io.stream.com.models.Like;
import io.stream.com.models.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<Like> findByMovie(Movie movie);
    
    @Query("SELECT count(a)>0 FROM Like a WHERE a.movie.movieId = :movieId AND a.user.userId = :userId")
    public Boolean isExistByMovieIdAndUserId(Long movieId, Long userId);

}
