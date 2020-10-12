package io.stream.com.services;

import java.util.List;
import java.util.Optional;

import io.stream.com.repositories.MovieRepository;
import io.stream.com.repositories.WatchLaterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.stream.com.cache.MovieCache;
import io.stream.com.controllers.exceptions.MovieNotFoundException;
import io.stream.com.mappers.MovieMapper;
import io.stream.com.models.Movie;
import io.stream.com.models.WatchLater;
import io.stream.com.models.dtos.MovieDto;
import io.stream.com.models.enums.GenreType;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private MovieCache movieCache;

	@Autowired
	private WatchLaterRepository watchLaterRepository;

	public Page<Movie> getAll(Pageable pageable){

		return repository.findAll(pageable);
	}

	// Update Popularity Score scheduled task needs this
	public List<Movie> getAll(){

		return repository.findAll();
	}

	public Movie getById(Long id) {
		Optional<Movie> movie = repository.findById(id);
		
		if(!movie.isPresent())
			throw new MovieNotFoundException(id);

		 return movie.get(); 
	}

	public void deleteById(Long id){
		
		repository.deleteById(id);
	}

	public Movie update(MovieDto movieDto, Long id){
		Optional<Movie> optionalMovie = repository.findById(id);

		if(!optionalMovie.isPresent())
			throw new MovieNotFoundException(id);

		Movie movie = optionalMovie.get();
		movie.setTitle(movieDto.getTitle());
		movie.setDescription(movieDto.getDescription());

		return repository.save(movie);
	}

	public Movie create(MovieDto movieDto) {

		return repository.save(MovieMapper.map(movieDto)); 
	}

	public Page<Movie> getMostViewed(Pageable pageable){

		return repository.findAllByOrderByViewCountDesc(pageable);
	}
	
	public Page<Movie> getTopRated(Pageable pageable){

		return repository.findTopRated(pageable);
	}

	public Page<Movie> getMostLiked(Pageable pageable){

		return repository.findAllByOrderByLikeCountDesc(pageable);
	}

	public Page<Movie> advancedSearch(String title, float rating, int release, Pageable pageable){

		return repository.advancedSearch(title, rating, release, pageable);
	}

	public List<Movie> quickSearch(String title){ 

		return repository.findTop6ByTitleContainingIgnoreCase(title.toLowerCase()); 
	}

	public Page<Movie> getByGenreType(GenreType genre, Pageable pageable) { 

		return repository.findByGenreType(genre, pageable); 
	}

	public void updatePopularityScore(Long movieId, double popularityScore){ 

		repository.updatePopularityScore(movieId, popularityScore); 
	}

	public Page<Movie> getTrending(Pageable pageable) { 

		return repository.findAllByOrderByPopularityScoreDesc(pageable); 
	}

	public void increaseViewCount(String ip, Long movieId) { 

		movieCache.pushToUniqueViews(movieId, ip); 
	}
	
	public void updateViewCount(Long movieId, int newViews){ 

		repository.updateViewCount(movieId, newViews); 
	}

	public void increamentViewCountById(Long id){

		repository.increamentViewCountById(id);
	}

	public Page<WatchLater> getWatchLaterList(Pageable pageable) {

		return watchLaterRepository.findAll(pageable);
	}

	public Page<WatchLater> getWatchLaterListByUserId(Pageable pageable, Long userId) {
		
		return watchLaterRepository.findByUser(pageable, userId);
	}
}
