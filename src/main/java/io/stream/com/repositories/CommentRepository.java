package io.stream.com.repositories;

import io.stream.com.models.Comment;
import io.stream.com.models.dtos.CommentDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{ 

    @Query("SELECT NEW io.stream.com.models.dtos.CommentDto(a.movie.movieId, a.user.avatarId, a.date, a.user.username, a.body) FROM Comment a WHERE a.movie.movieId = :movieId")
    public Page<CommentDto> findByMovieId(Long movieId, Pageable pageable);
    
    @Query("SELECT NEW io.stream.com.models.dtos.CommentDto(a.movie.movieId, a.user.avatarId, a.date, a.user.username, a.body) FROM Comment a WHERE a.user.username = :username")
    public Page<CommentDto> findByUsername(String username, Pageable pageable);

    @Query("SELECT NEW io.stream.com.models.dtos.CommentDto(a.movie.movieId, a.user.avatarId, a.date, a.user.username, a.body) FROM Comment a")
    public Page<CommentDto> findAllDto(Pageable pageable);
}
