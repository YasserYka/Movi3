package io.stream.com.services;

import io.stream.com.mappers.CommentMapper;
import io.stream.com.models.Comment;
import io.stream.com.models.Movie;
import io.stream.com.models.dtos.CommentDto;
import io.stream.com.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAll() {

        return commentRepository.findAll();
    }

    public Page<CommentDto> getAll(Pageable pageable){

        return commentRepository.findAllDto(pageable);
    }

    public Page<CommentDto> getByMovieId(Long movieId, Pageable pageable) {

        return commentRepository.findByMovieId(movieId, pageable);
    }

    public Page<CommentDto> getByUsername(String username, Pageable pageable) {

        return commentRepository.findByUsername(username, pageable);
    }

    public void create(CommentDto commentDto){
        Movie movie = movieService.getById(commentDto.getMovieId());

        commentRepository.save(CommentMapper.map(movie, commentDto, userService.getCurrentLoggedInUser()));
    }
}
