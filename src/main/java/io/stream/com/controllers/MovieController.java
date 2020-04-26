package io.stream.com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.services.MovieService;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
	
    @Autowired
    private MovieService service;


    @RequestMapping
    public List<Movie> getAll(){ return service.getAll(); }
    
    @RequestMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@RequestParam("id") Long id) {
    	return Optional.ofNullable(service.getById(id))
    			.map(movie -> ResponseEntity.ok(movie.get()))
    			.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping("/beingwatched")
    public ResponseEntity<List<Movie>> getMovie(){
        return null;
    }
}
