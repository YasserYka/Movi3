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
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    public List<Like> getAll(){ 
        return likeRepository.findAll(); 
    }

    public void save(LikeDto likeDto) {
        Movie movie = movieService.getById(likeDto.getMovieId);
        User currentLoggedInUser = userService.getCurrentLoggedInUser();

        /*if(!likeRepository.isExistByMovieIdAndUserId(movie.getMovieId(), currentLoggedInUser.getUserId())){    
            likeRepository.save(LikeMapper.map(likeDto, movie, currentLoggedInUser));
            movieService.increamentViewCountById(movie.getMovieId());
        }*/
        
    }
}
