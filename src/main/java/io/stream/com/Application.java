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

			movieRepository.save(Movie.builder().release(1009).title("notFakeMovie1").rating(4.1f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2013).title("notFakeMovie2").rating(3.8f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2018).title("notFakeMovie3").rating(5.9f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2020).title("notFakeMovie4").rating(6.5f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2009).title("notFakeMovie5").rating(7.3f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(1969).title("notFakeMovie6").rating(8.5f).imagePath("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());

		};
	}

}
