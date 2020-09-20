package io.stream.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.stream.com.models.WatchLater;

public interface WatchLaterRepository extends JpaRepository<WatchLater, Long>{ }