package io.stream.com.controllers;

import io.stream.com.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService service;

    @PostMapping("/api/v1/upload")
    public void uploadMovie(@RequestParam("file") MultipartFile file) { service.upload(file); }

}
