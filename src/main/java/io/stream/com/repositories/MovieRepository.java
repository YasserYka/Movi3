package io.stream.com.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.stream.com.models.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, Long>{

}
