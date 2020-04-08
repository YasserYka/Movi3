package io.stream.com.services;

import java.util.Optional;

import io.stream.com.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public Optional<Movie> getById(Long id) { return repository.findById(id); }

	public void uploadMultipartFile() {}


}
