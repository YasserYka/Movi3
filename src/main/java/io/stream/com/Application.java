package io.stream.com;

import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.repositories.MovieRepository;
import io.stream.com.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadBooks(MovieRepository movieRepository, UserRepository userRepository) {
		return (args) -> {
			userRepository.save(User.builder().username("user").password("pass").email("user@gmail.com").accountNonExpired(true).accountNotLocked(true).credentialsNonExpired(true).enabled(true).build());
			movieRepository.save(Movie.builder().originalFilename("sample.mp4").storedInS3(false).description("idk romantic?").build());
		};
	}

}
