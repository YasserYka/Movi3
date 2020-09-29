package io.stream.com.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.models.WatchLater;
import io.stream.com.models.dtos.MovieDto;
import io.stream.com.models.enums.GenreType;
import io.stream.com.services.MovieService;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
	
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<Movie>> getAll(@PageableDefault(size = 12) Pageable pageable){

        return new ResponseEntity<>(service.getAll(pageable), HttpStatus.OK); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(service.getById(id), HttpStatus.OK); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable("id") Long id){

        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK); 
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody MovieDto movieDto){

        return new ResponseEntity<>(service.create(movieDto), HttpStatus.OK); 
    }

    @PutMapping("/{id]")
    public ResponseEntity<?> update(@RequestBody MovieDto movieDto, @PathVariable("id") Long id){

        return new ResponseEntity<>(service.update(movieDto, id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/advancedsearch")
    public ResponseEntity<Page<Movie>> advancedSearch(@RequestParam Optional<String> title, @RequestParam Optional<Float> rating, @RequestParam Optional<Integer> release, @PageableDefault(size = 10) Pageable pageable){

        return new ResponseEntity<>(service.advancedSearch(title.orElse(null), rating.orElse(0.0f), release.orElse(0), pageable), HttpStatus.OK); 
    }

    @GetMapping("/quicksearch")
    public ResponseEntity<List<Movie>> quickSearch(@RequestParam Optional<String> title){ 

        return new ResponseEntity<>(service.quickSearch(title.orElse("")), HttpStatus.OK); 
    }

    @GetMapping("/trending")
    public ResponseEntity<Page<Movie>> trending(@PageableDefault(size = 12) Pageable pageable){ 

        return new ResponseEntity<>(service.getTrending(pageable), HttpStatus.OK);
    }

    @GetMapping("/mostliked")
    public ResponseEntity<Page<Movie>> getMostLiked(@PageableDefault(size = 12) Pageable pageable){ 

        return new ResponseEntity<>(service.getMostLiked(pageable), HttpStatus.OK);
    }

    @GetMapping("/toprated")
    public ResponseEntity<Page<Movie>> getTopRated(@PageableDefault(size = 12) Pageable pageable){ 

        return new ResponseEntity<>(service.getTopRated(pageable), HttpStatus.OK);
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
    public ResponseEntity<Page<WatchLater>> getWatchLaterListByUserId(@PageableDefault(size = 12) Pageable pageable, @PathVariable("userid") Long userId) {

        return new ResponseEntity<>(service.getWatchLaterListByUserId(pageable, userId), HttpStatus.OK);
    }
}
