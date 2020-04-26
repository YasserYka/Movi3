package io.stream.com.controllers;


import io.stream.com.models.dtos.LikeDto;
import io.stream.com.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {

    @Autowired
    private LikeService service;

    @GetMapping
    public ResponseEntity<?> getAll(){ return ResponseEntity.ok(service.getAll()); }

    @PostMapping
    public ResponseEntity<?> add(LikeDto likeDto){
        service.save(likeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
