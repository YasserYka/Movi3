package io.stream.com.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import io.stream.com.repositories.MovieRepository;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;

import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private CacheService cacheService;

	@Value("${upload.path}")
	private String uploadPath;

	private static final int NUMBER_OF_MOVIES_BEING_WATCHED = 6;

	public List<Movie> getAll(){
		return repository.findAll();
	}

	public Optional<Movie> getById(Long id) { return repository.findById(id); }

	@SneakyThrows(IOException.class)
	public void upload(MultipartFile multipartFile) { Files.copy(multipartFile.getInputStream(), Paths.get(uploadPath + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); }

	public void save(Movie movie) { repository.save(movie); }

	public List<Movie> advancedSearch(String title, float rating, int release){
		return repository.advancedSearch(title, rating, release);
	}

	public List<Movie> findByTitle(String title){  return repository.findByTitle(title); }

	public List<Movie> getByGenreType(GenreType genre) { return repository.findByGenreType(genre); }
	
	public List<Movie> get6MoviesBeingWatched() { return cacheService.getListOfMoviesBeingWatched(); }

    public void addToMoviesBeingWatched(Movie movie){
        if(cacheService.getSizeMoviesBeingWatched() == NUMBER_OF_MOVIES_BEING_WATCHED)
			cacheService.rightPopMovieBeingWatched();
		cacheService.leftPushMovieBeingWatched(movie);
    }

	public List<Movie> get6MostViewedMovies() { return cacheService.getListOfMoviesBeingWatched(); }

	public void movieViewed(String ip, Long movieId) { cacheService.putRecentViewedMovie(ip, movieId); }

	public void updateViewCount(Long movieId, int newViews){ repository.updateViewCount(movieId, newViews); }

	public void updatePopularityScore(Long movieId, double popularityScore){ repository.updatePopularityScore(movieId, popularityScore); }

	public List<Movie> trending() { return repository.findAllByOrderByPopularityScoreDesc(); }

}
