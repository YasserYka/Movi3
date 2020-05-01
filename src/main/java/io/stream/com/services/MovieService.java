package io.stream.com.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import io.stream.com.repositories.MovieRepository;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Value("${upload.path}")
	private String uploadPath;

	public List<Movie> getAll(){
		return repository.findAll();
	}

	public Optional<Movie> getById(Long id) { return repository.findById(id); }

	@SneakyThrows(IOException.class)
	public void upload(MultipartFile multipartFile) { Files.copy(multipartFile.getInputStream(), Paths.get(uploadPath + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); }

	public void save(Movie movie) { repository.save(movie); }

}
