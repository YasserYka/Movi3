package io.stream.com.repositories;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public List<Movie> findTop6ByOrderByViewCountDesc();

    @Query("SELECT a FROM Movie a WHERE (:title is null or a.title like :title) AND (:rating = 0.0f or a.rating >= :rating) AND (:release = 0 or a.release >= :release)")
    public Page<Movie> advancedSearch(String title, float rating, int release, Pageable pageable);


    @Query(value = "SELECT * FROM Movie WHERE title LIKE :title% LIMIT 6", nativeQuery = true)
    public List<Movie> findByTitle(String title);

    @Query("SELECT a FROM Movie a join a.genres g where g.type = :genre")
    public Page<Movie> findByGenreType(GenreType genre, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Movie a set a.viewCount = a.viewCount + :newViews WHERE a.id = :id")
    public void updateViewCount(Long id, int newViews);

    @Transactional
    @Modifying
    @Query("UPDATE Movie a set a.popularityScore = :score WHERE a.id = :id")
    public void updatePopularityScore(Long id, double score);

    public Page<Movie> findAllByOrderByPopularityScoreDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Movie a set a.viewCount = a.viewCount + 1 WHERE a.id = :id")
    public void increamentViewCountById(Long id);
    
    public Page<Movie> findAllByOrderByViewCountDesc(Pageable pageable);

    public Page<Movie> findAllByOrderByLikeCountDesc(Pageable pageable);

    @Query("SELECT a FROM Movie a ORDER BY rating ASC")
    public Page<Movie> findTopRated(Pageable pageable);
}
