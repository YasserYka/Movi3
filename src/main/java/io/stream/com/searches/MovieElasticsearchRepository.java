package io.stream.com.searches;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import io.stream.com.models.Movie;

@Repository
public interface MovieElasticsearchRepository extends ElasticsearchRepository<Movie, Long> { }