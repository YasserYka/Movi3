package io.stream.com.repositories;

import io.stream.com.models.Comment;
import io.stream.com.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentRepository, Long>{
    public List<Comment> findByMovie(Movie movie);
}
