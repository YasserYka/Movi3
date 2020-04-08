package io.stream.com.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.stream.com.models.Movie;
import io.stream.com.services.MovieService;
import reactor.core.publisher.Mono;

@Component
public class MovieHandler {
	
	@Autowired
	private MovieService service;
	
	public Mono<ServerResponse> getResourceRegion(ServerRequest request){
		Long id = Long.valueOf(request.pathVariable("id"));

		Mono<Movie> monoMovie = service.getById(id);
		
		return Mono.empty();
	}
}
