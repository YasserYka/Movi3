package io.stream.com.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.stream.com.models.Movie;
import io.stream.com.services.MovieService;

@Component
public class MovieHandler {
	
	@Autowired
	private MovieService movieService;
}
