package io.stream.com.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.stream.com.models.WatchLater;

public interface WatchLaterRepository extends JpaRepository<WatchLater, Long>{

    @Query("SELECT movies FROM WatchLater")
    public Page<WatchLater> findAll(Pageable pageable);

    @Query("SELECT a.movies FROM WatchLater a WHERE a.user.id = :id")
    public Page<WatchLater> findByUser(Pageable pageable, Long id);

 }