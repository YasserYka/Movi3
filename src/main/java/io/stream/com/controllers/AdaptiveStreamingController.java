package io.stream.com.controllers;

import java.net.MalformedURLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.stream.com.models.Movie;
import io.stream.com.services.MovieService;

@RestController
@RequestMapping("/api/v1/manifest")
public class AdaptiveStreamingController {
	
	@Autowired
	private MovieService service;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/{id}")
	public ResponseEntity<UrlResource> getManifest(@PathVariable("id") Long id) throws MalformedURLException {
		
		Optional<Movie> optionalManifest = service.getById(id);
		
		if(!optionalManifest.isPresent()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		UrlResource manifestResource = new UrlResource(String.format("file:%s%s", uploadPath, optionalManifest.get().getOriginalFilename()));
			
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaTypeFactory.getMediaType("application/dash+xml").orElse(MediaType.APPLICATION_OCTET_STREAM)).body(manifestResource);
	}
}
