package io.stream.com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.services.MovieService;

@RestController
@RequestMapping("/api/v1/views")
public class ViewController {

    @Autowired
    private MovieService movieService;

	@GetMapping("/{movieId}")
	public ResponseEntity<?> viewd(@PathVariable("movieId") Long movieId, HttpServletRequest HttpServletRequest){

        movieService.increaseViewCount(HttpServletRequest.getRemoteAddr(), movieId);

        return new ResponseEntity<>(HttpStatus.OK);    
    }
    
}