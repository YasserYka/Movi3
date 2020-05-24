package io.stream.com.services;

import io.stream.com.mappers.LikeMapper;
import io.stream.com.models.Like;
import io.stream.com.models.Movie;
import io.stream.com.models.dtos.LikeDto;
import io.stream.com.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    public List<Like> getAll(){ return likeRepository.findAll(); }

    public void save(LikeDto likeDto) {
        Optional<Movie> optionalMovie = movieService.getById(likeDto.getMovieId);

        //TODO check if user already liked video?

        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            likeRepository.save(LikeMapper.map(likeDto, movie, userService.getCurrentLoggedInUser()));
            movieService.increamentViewCountById(movie.getMovieId());
        }
    }
}
