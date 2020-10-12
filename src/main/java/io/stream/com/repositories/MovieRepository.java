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

    @Query("SELECT m FROM Movie m WHERE (:title is null or m.title like :title) AND (:rating = 0.0f or m.rating >= :rating) AND (:release = 0 or m.release >= :release)")
    public Page<Movie> advancedSearch(String title, float rating, int release, Pageable pageable);

    public List<Movie> findTop6ByTitleContainingIgnoreCase(String title);

    @Query("SELECT m FROM Movie m join m.genres g where g.type = :genre")
    public Page<Movie> findByGenreType(GenreType genre, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Movie m set m.viewCount = m.viewCount + :newViews WHERE m.id = :id")
    public void updateViewCount(Long id, int newViews);

    @Transactional
    @Modifying
    @Query("UPDATE Movie m set m.popularityScore = :score WHERE m.id = :id")
    public void updatePopularityScore(Long id, double score);

    public Page<Movie> findAllByOrderByPopularityScoreDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Movie m set m.viewCount = m.viewCount + 1 WHERE m.id = :id")
    public void increamentViewCountById(Long id);
    
    public Page<Movie> findAllByOrderByViewCountDesc(Pageable pageable);

    public Page<Movie> findAllByOrderByLikeCountDesc(Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY rating ASC")
    public Page<Movie> findTopRated(Pageable pageable);
}
