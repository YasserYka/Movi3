package io.stream.com.repositories;

import io.stream.com.models.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public List<Movie> findTop6ByOrderByViewCountDesc();
 }
