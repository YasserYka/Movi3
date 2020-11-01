package io.stream.com;

import io.stream.com.cache.AuthCache;
import io.stream.com.cache.EmailCache;
import io.stream.com.models.Comment;
import io.stream.com.models.Genre;
import io.stream.com.models.Like;
import io.stream.com.models.MQVideoProcessingMessage;
import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.models.WatchLater;
import io.stream.com.models.enums.GenreType;
import io.stream.com.models.enums.VideoProcessType;
import io.stream.com.models.enums.Roles;
import io.stream.com.repositories.CommentRepository;
import io.stream.com.repositories.GenreRepository;
import io.stream.com.repositories.LikeRepository;
import io.stream.com.repositories.MovieRepository;
import io.stream.com.repositories.UserRepository;
import io.stream.com.repositories.WatchLaterRepository;
import io.stream.com.services.EmailService;
import io.stream.com.services.MQService;
import io.stream.com.services.MovieService;
import io.stream.com.utils.TimeUtil;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import java.util.Date;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadBooks(UserRepository userRepository) {
		return (args) -> {

			User user1 = User.builder().roles(Roles.ROLE_ADMIN.name()).bio("regular movie fan").creationDate(new Date())
					.lastSeen(TimeUtil.dateAfter(23)).fullName("User Bin User").username("user")
					.password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").avatarId(1)
					.accountNonExpired(true).accountNotLocked(true).credentialsNonExpired(true).enabled(true).build();

			userRepository.save(user1);

		};
	}

	// Because Jackson-Serializer triggers lazy loading in any cases
	@Bean
	protected Module module() {
		return new Hibernate5Module();
	}

}
