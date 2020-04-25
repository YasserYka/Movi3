package io.stream.com.controllers;

import io.stream.com.models.dtos.Comment;
import io.stream.com.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment){ return new ResponseEntity<>(HttpStatus.CREATED); }

    @GetMapping
    public ResponseEntity<List<Comment>>  getAll(){ return ResponseEntity.ok(service.getAllComments()); }

    @GetMapping("movie/{id}")
    public ResponseEntity<List<Comment>> getAllCommentsOfMovieId(@PathVariable Long id){

        return  ResponseEntity.ok(service.getAllCommentsOfMovieId(id));
    }
}
