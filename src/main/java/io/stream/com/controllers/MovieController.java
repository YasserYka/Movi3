package io.stream.com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.models.enums.GenreType;
import io.stream.com.services.MovieService;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
	
    @Autowired
    private MovieService service;

    @GetMapping
    public List<Movie> getAll(){ return service.getAll(); }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@RequestParam("id") Long id) {
    	return Optional.ofNullable(service.getById(id))
    			.map(movie -> ResponseEntity.ok(movie.get()))
    			.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/advancedsearch")
    public ResponseEntity<List<Movie>> advancedSearch(@RequestParam Optional<String> title, @RequestParam Optional<Float> rating, @RequestParam Optional<Integer> release){
        return new ResponseEntity<>(service.advancedSearch(title.orElse(null), rating.orElse(0.0f), release.orElse(0)), HttpStatus.OK); 
    }

    @GetMapping("/quicksearch")
    public ResponseEntity<List<Movie>> quickSearch(@RequestParam Optional<String> title){ 
        return new ResponseEntity<>(service.findByTitle(title.orElse(null)), HttpStatus.OK); 
    }

    @GetMapping("/trending")
    public ResponseEntity<List<Movie>> trending(){ 
        return new ResponseEntity<>(service.trending(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Movie>> getByGenreType(@RequestParam Optional<GenreType> genre){
        return new ResponseEntity<>(service.getByGenreType(genre.orElse(null)), HttpStatus.OK);
    }
}
