package io.stream.com.controllers;

import io.stream.com.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UploadController {

    @Autowired
    private MovieService service;

    @PostMapping("/upload")
    public void uploadMovie() { service.uploadMultipartFile(); }

}
