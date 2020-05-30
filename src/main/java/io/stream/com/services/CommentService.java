package io.stream.com.services;

import io.stream.com.mappers.CommentMapper;
import io.stream.com.models.Comment;
import io.stream.com.models.Movie;
import io.stream.com.models.dtos.CommentDto;
import io.stream.com.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<CommentDto> getAllCommentsDto(){
        return commentRepository.findAllDto();
    }

    public List<CommentDto> getAllCommentsOfMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    public List<CommentDto> getAllCommentsOfUsername(String username) {
        return commentRepository.findByUsername(username);
    }

    public void save(CommentDto commentDto){
        Optional<Movie> optionalMovie = movieService.getById(commentDto.getMovieId());

        if(optionalMovie.isPresent())
            commentRepository.save(CommentMapper.map(optionalMovie.get(), commentDto, userService.getCurrentLoggedInUser()));
    }
}
