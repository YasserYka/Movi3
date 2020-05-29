package io.stream.com.repositories;

import io.stream.com.models.Comment;
import io.stream.com.models.dtos.CommentDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{ 

    @Query("SELECT a FROM Comment a WHERE a.movie.movieId = :movieId")
    public List<Comment> findByMovieId(Long movieId);
    
    @Query("SELECT a FROM Comment a WHERE a.user.username = :username")
    public List<Comment> findByUsername(String username);

    @Query("SELECT NEW io.stream.com.models.dtos.CommentDto(a.movie.movieId, a.user.avatarId, a.date, a.user.username, a.body) FROM Comment a")
    public List<CommentDto> findAllDto();
}
