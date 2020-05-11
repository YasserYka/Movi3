package io.stream.com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.stream.com.models.Genre;
import io.stream.com.models.Movie;

public interface GenreRepository extends JpaRepository<Genre, Long>{ 

    public List<Genre> findByMovie(Movie movie);
}