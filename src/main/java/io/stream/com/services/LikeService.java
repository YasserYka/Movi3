package io.stream.com.services;

import io.stream.com.mappers.LikeMapper;
import io.stream.com.models.Like;
import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.models.dtos.LikeDto;
import io.stream.com.repositories.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private AuthService authService;

    public List<Like> getAll(){ 
        return likeRepository.findAll(); 
    }

    public void create(LikeDto likeDto) {
        Movie movie = movieService.getById(likeDto.getMovieId);
        User currentLoggedInUser = authService.getCurrentLoggedInUser();

        // User can only like movie once
        if(!likeRepository.isExistByMovieIdAndUserId(movie.getMovieId(), currentLoggedInUser.getUserId())){    
            likeRepository.save(LikeMapper.map(likeDto, movie, currentLoggedInUser));
            movieService.increamentViewCountById(movie.getMovieId());
        }
    }
}
