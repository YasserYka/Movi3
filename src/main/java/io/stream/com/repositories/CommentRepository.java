package io.stream.com.repositories;

import io.stream.com.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{ 

    @Query("SELECT a FROM Comment a WHERE a.movie.movieId = :movieId")
    public List<Comment> findByMovieId(Long movieId); 
    
}
