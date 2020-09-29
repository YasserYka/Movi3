package io.stream.com.controllers;

import io.stream.com.models.dtos.CommentDto;
import io.stream.com.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDto commentDto){
        service.create(commentDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CommentDto>> getAll(@PageableDefault(size = 12) Pageable pageable){ 

        return new ResponseEntity<>(service.getAll(pageable), HttpStatus.OK); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<CommentDto>> getByMovieId(@PageableDefault(size = 12) Pageable pageable, @PathVariable("id") Long id){ 

        return new ResponseEntity<>(service.getByMovieId(id, pageable), HttpStatus.OK);  
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Page<CommentDto>> getByUsername(@PageableDefault(size = 12) Pageable pageable, @PathVariable("username") String username){ 

        return new ResponseEntity<>(service.getByUsername(username, pageable), HttpStatus.OK); 
    }
}
