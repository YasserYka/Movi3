package io.stream.com.mappers;

import io.stream.com.models.Like;
import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.models.dtos.LikeDto;

public class LikeMapper {
    public static Like map(LikeDto voteDto, Movie movie, User user) { return Like.builder().movie(movie).user(user).build(); }
}
