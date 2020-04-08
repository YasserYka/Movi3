package io.stream.com.services;

import io.stream.com.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private S3Client s3Client;


}
