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

	public static void main(String[] args) { SpringApplication.run(Application.class, args); }

	@Bean
	public CommandLineRunner loadBooks(MovieRepository movieRepository, UserRepository userRepository) {
		return (args) -> {
			userRepository.save(User.builder().username("user").password("pass").email("user@gmail.com").profileImageId(0).accountNonExpired(true).accountNotLocked(true).credentialsNonExpired(true).enabled(true).build());

			movieRepository.save(Movie.builder().release(2009).title("WEREWOLF IN A GIRLS").viewCount(0).likeCount(0).rating(4.1f).imageUrl("posters/234555.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2013).title("THE GRAND TOUR").viewCount(0).likeCount(0).rating(3.8f).imageUrl("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2018).title("TARANTULA!").viewCount(0).likeCount(0).rating(5.9f).imageUrl("posters/345333.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2020).title("EARTH").viewCount(0).likeCount(0).rating(6.5f).imageUrl("posters/374575.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(2009).title("EUROPA").viewCount(0).likeCount(0).rating(7.3f).imageUrl("posters/457474.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());
			movieRepository.save(Movie.builder().release(1969).title("The WASP WOMAN").viewCount(0).likeCount(0).rating(8.5f).imageUrl("posters/854243.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build());

		};
	}

}
