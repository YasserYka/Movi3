package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.stream.com.models.Genre;
import io.stream.com.repositories.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    public void saveAll(List<Genre> genres){ repository.saveAll(genres); }
    
}