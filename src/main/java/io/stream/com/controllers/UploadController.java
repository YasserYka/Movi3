package io.stream.com.controllers;

import io.stream.com.models.Movie;
import io.stream.com.models.dtos.MovieDto;
import io.stream.com.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService service;

    @PostMapping("/api/v1/upload")
    public ResponseEntity<?> uploadMovie(@RequestPart("file") MultipartFile file, @RequestPart("movie") MovieDto movieDto) { 
        service.upload(file, movieDto); 
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
