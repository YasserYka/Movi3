package io.stream.com.services;

import com.amazonaws.services.kms.model.NotFoundException;
import io.stream.com.models.Comment;
import io.stream.com.models.Movie;
import io.stream.com.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        return null;
    }

    public List<Comment> getAllCommentsOfMovieId(Long movieId) {
        Optional<Movie> optionalMovie = movieService.getById(movieId);

        if(!optionalMovie.isPresent())
            return null;

        return commentRepository.findByMovie(optionalMovie.get());
    }
}
