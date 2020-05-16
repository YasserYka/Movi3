package io.stream.com.mappers;

import io.stream.com.models.Movie;
import io.stream.com.models.dtos.MovieDto;

public class MovieMapper {

    public static Movie map(MovieDto movieDto){
        return Movie.builder()
            .title(movieDto.getTitle())
            .description(movieDto.getDescription())
            .release(movieDto.getRelease())
            .build();
    }
    
}