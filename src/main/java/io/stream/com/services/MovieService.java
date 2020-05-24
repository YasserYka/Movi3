package io.stream.com.services;

import java.util.List;
import java.util.Optional;

import io.stream.com.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private CacheService cacheService;

	public List<Movie> getAll(){
		return repository.findAll();
	}

	public Optional<Movie> getById(Long id) {
		 return repository.findById(id); 
	}

	public void save(Movie movie) {
		 repository.save(movie); 
		}

	public List<Movie> advancedSearch(String title, float rating, int release){
		return repository.advancedSearch(title, rating, release);
	}

	public List<Movie> findByTitle(String title){ 
		return repository.findByTitle(title); 
	}

	public List<Movie> getByGenreType(GenreType genre) { 
		return repository.findByGenreType(genre); 
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

	public List<Movie> trending() { 
		return repository.findAllByOrderByPopularityScoreDesc(); 
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
