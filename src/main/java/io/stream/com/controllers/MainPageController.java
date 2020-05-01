package io.stream.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.services.MainPageService;

@RestController
@RequestMapping("/api/v1/mainpage")
public class MainPageController {


    @Autowired
    private MainPageService service;

    @PostMapping("/beingwatchedrightnow")
    public ResponseEntity<?> addMovieBeingWatched(){
        //TODO: DTO with id movie?
        //service.addToMoviesBeingWatched();
        return new ResponseEntity<>(HttpStatus.CREATED);    
    }

    @GetMapping("/beingwatchedrightnow")
    public List<Movie> get6MoviesBeingWatched(){ return service.get6MoviesBeingWatched(); }

}