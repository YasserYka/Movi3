package io.stream.com.services;

import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import io.stream.com.repositories.MovieRepository;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class MovieService {

	private MovieRepository repository;
	
	public Mono<Movie> getById(Long id) {return repository.findById(id);}
}
