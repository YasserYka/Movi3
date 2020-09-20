package io.stream.com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.models.WatchLater;
import io.stream.com.models.enums.GenreType;
import io.stream.com.services.MovieService;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
	
    @Autowired
    private MovieService service;

    @GetMapping
    public Page<Movie> getAll(@PageableDefault(size = 12) Pageable pageable){
        return service.getAll(pageable); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@RequestParam("id") Long id) {
    	return Optional.ofNullable(service.getById(id))
    			.map(movie -> ResponseEntity.ok(movie.get()))
    			.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/advancedsearch")
    public ResponseEntity<Page<Movie>> advancedSearch(@RequestParam Optional<String> title, @RequestParam Optional<Float> rating, @RequestParam Optional<Integer> release, @PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity<>(service.advancedSearch(title.orElse(null), rating.orElse(0.0f), release.orElse(0), pageable), HttpStatus.OK); 
    }

    @GetMapping("/quicksearch")
    public ResponseEntity<List<Movie>> quickSearch(@RequestParam Optional<String> title){ 
        return new ResponseEntity<>(service.findByTitle(title.orElse(null)), HttpStatus.OK); 
    }

    @GetMapping("/trending")
    public ResponseEntity<Page<Movie>> trending(@PageableDefault(size = 12) Pageable pageable){ 
        return new ResponseEntity<>(service.getTrending(pageable), HttpStatus.OK);
    }

    @GetMapping("/mostLiked")
    public ResponseEntity<Page<Movie>> getMostLiked(@PageableDefault(size = 12) Pageable pageable){ 
        return new ResponseEntity<>(service.getMostLiked(pageable), HttpStatus.OK);
    }

    @GetMapping("/mostviewed")
    public ResponseEntity<Page<Movie>> getMostViewed(@PageableDefault(size = 12) Pageable pageable){ 
        return new ResponseEntity<>(service.getMostViewed(pageable), HttpStatus.OK);
    }

    @GetMapping("/searchbygenre")
    public ResponseEntity<Page<Movie>> getByGenreType(@RequestParam Optional<GenreType> genre, @PageableDefault(size = 12) Pageable pageable){
        return new ResponseEntity<>(service.getByGenreType(genre.orElse(null), pageable), HttpStatus.OK);
    }

    @GetMapping("/watchlater")
    public ResponseEntity<Page<WatchLater>> getWatchLaterList(@PageableDefault(size = 12) Pageable pageable){
        return new ResponseEntity<>(service.getWatchLaterList(pageable), HttpStatus.OK);
    }

    @GetMapping("/watchlater/{userid}")
    public ResponseEntity<Page<WatchLater>> getWatchLaterListByUser(@PageableDefault(size = 12) Pageable pageable, @RequestParam("userid") Long userId) {
        return new ResponseEntity<>(service.getWatchLaterListByUser(pageable, userId), HttpStatus.OK);
    }
}
