package io.stream.com.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/views")
public class ViewController {

	@GetMapping("/{movieId}")
	public ResponseEntity<?> getRegion(@PathVariable("movieId") Long movieId){



        return new ResponseEntity<>(HttpStatus.OK);    
    }
    
}