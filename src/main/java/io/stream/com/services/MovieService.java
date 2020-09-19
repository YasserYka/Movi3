package io.stream.com.services;

import java.util.List;
import java.util.Optional;

import io.stream.com.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private CacheService cacheService;

	public Page<Movie> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}

	public List<Movie> getAll(){
		return repository.findAll();
	}

	public Optional<Movie> getById(Long id) {
		 return repository.findById(id); 
	}

	public void save(Movie movie) {
		 repository.save(movie); 
	}

	public Page<Movie> getMostViewed(Pageable pageable){
		return repository.findAllByOrderByViewCountDesc(pageable);
	}

	public Page<Movie> getMostLiked(Pageable pageable){
		return repository.findAllByOrderByLikeCountDesc(pageable);
	}

	public Page<Movie> advancedSearch(String title, float rating, int release, Pageable pageable){
		return repository.advancedSearch(title, rating, release, pageable);
	}

	public List<Movie> findByTitle(String title){ 
		return repository.findByTitle(title); 
	}

	public Page<Movie> getByGenreType(GenreType genre, Pageable pageable) { 
		return repository.findByGenreType(genre, pageable); 
	}
	
	public List<Movie> get6MoviesBeingWatched() { 
		return cacheService.getListOfMoviesBeingWatched(); 
	}

    public void addToMoviesBeingWatched(Movie movie){
        if(cacheService.getSizeMoviesBeingWatched() == 6)
			cacheService.rightPopMovieBeingWatched();
		cacheService.leftPushMovieBeingWatched(movie);
    }

	public List<Movie> get6MostViewedMovies() { 
		return cacheService.getListOfMoviesBeingWatched(); 
	}

	public void updatePopularityScore(Long movieId, double popularityScore){ 
		repository.updatePopularityScore(movieId, popularityScore); 
	}

	public Page<Movie> getTrending(Pageable pageable) { 
		return repository.findAllByOrderByPopularityScoreDesc(pageable); 
	}

	public void viewed(String ip, Long movieId) { 
		cacheService.putRecentViewedMovie(ip, movieId); 
	}

	public void updateViewCount(Long movieId, int newViews){ 
		repository.updateViewCount(movieId, newViews); 
	}

	public void increamentViewCountById(Long id){
		repository.increamentViewCountById(id);
	}

}
