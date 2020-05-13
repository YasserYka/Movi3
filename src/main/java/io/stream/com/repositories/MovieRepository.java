package io.stream.com.repositories;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public List<Movie> findTop6ByOrderByViewCountDesc();

    @Query("SELECT a FROM Movie a WHERE (:title is null or a.title like :title) AND (:rating = 0.0f or a.rating >= :rating) AND (:release = 0 or a.release >= :release)")
    public List<Movie> advancedSearch(String title, float rating, int release);


    @Query("SELECT a FROM Movie a WHERE a.title LIKE title")
    public List<Movie> findByTitle(String title);

    @Query("SELECT a FROM Movie a join a.genres g where g.type = :genre")
    public List<Movie> findByGenreType(GenreType genre);
}
