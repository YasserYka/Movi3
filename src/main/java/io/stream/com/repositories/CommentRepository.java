package io.stream.com.repositories;

import io.stream.com.models.Comment;
import io.stream.com.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{ 
    public List<Comment> findByMovie(Movie movie); 
}
