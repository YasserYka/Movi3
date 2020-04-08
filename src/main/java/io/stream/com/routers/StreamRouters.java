package io.stream.com.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


import io.stream.com.handlers.Handler;
import io.stream.com.handlers.MovieHandler;

@Configuration
public class StreamRouters {

	@Bean
	public RouterFunction<ServerResponse> route(MovieHandler movieHandler){
		
	}
}