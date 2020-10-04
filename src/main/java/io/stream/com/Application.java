package io.stream.com;

import io.stream.com.cache.EmailCache;
import io.stream.com.models.Comment;
import io.stream.com.models.Genre;
import io.stream.com.models.Like;
import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.models.WatchLater;
import io.stream.com.models.enums.GenreType;
import io.stream.com.models.enums.Roles;
import io.stream.com.repositories.CommentRepository;
import io.stream.com.repositories.GenreRepository;
import io.stream.com.repositories.LikeRepository;
import io.stream.com.repositories.MovieRepository;
import io.stream.com.repositories.UserRepository;
import io.stream.com.repositories.WatchLaterRepository;
import io.stream.com.services.EmailService;
import io.stream.com.services.MovieService;
import io.stream.com.utils.TimeUtil;

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
	public CommandLineRunner loadBooks(EmailCache emailCache, MovieRepository movieRepository,
			UserRepository userRepository, MovieService movieService, EmailService emailService,
			GenreRepository genreRepository, LikeRepository likeRepository, CommentRepository commentRepository,
			WatchLaterRepository watchLaterRepository) {
		return (args) -> {
			User user1 = User.builder().roles(Roles.ROLE_ADMIN.name()).bio("regular movie fan").creationDate(new Date())
					.lastSeen(TimeUtil.dateAfter(23)).fullName("User Bin User").username("user")
					.password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").avatarId(1)
					.accountNonExpired(true).accountNotLocked(true).credentialsNonExpired(true).enabled(true).build();

			userRepository.save(user1);

			Movie movie1 = Movie.builder().uploadDate(TimeUtil.dateAfter(12)).release(2020).title("CAPONE")
					.viewCount(1666).likeCount(23).rating(4.1f).posterUrl("capone.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie2 = Movie.builder().uploadDate(TimeUtil.dateAfter(25)).release(2020).title("BLOOD AND MONEY")
					.viewCount(414).likeCount(13).rating(3.8f).posterUrl("bloodandmoney.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie3 = Movie.builder().uploadDate(TimeUtil.dateAfter(33)).release(2020).title("COFFEE & KAREEM")
					.viewCount(15).likeCount(42).rating(5.9f).posterUrl("coffeekareem.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie4 = Movie.builder().uploadDate(TimeUtil.dateAfter(25)).release(2019).title("6 UNDERGROUND")
					.viewCount(166).likeCount(14).rating(6.5f).posterUrl("6underground.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie5 = Movie.builder().uploadDate(TimeUtil.dateAfter(62)).release(2019).title("JUMANJI")
					.viewCount(177).likeCount(144).rating(7.3f).posterUrl("jumanji.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie6 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2019).title("FROZEN 2")
					.viewCount(263).likeCount(100).rating(4.5f).posterUrl("frozen2.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie7 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2019).title("TRICK").viewCount(456)
					.likeCount(63).rating(5.1f).posterUrl("trick.jpg").regularStreamingUrl("sample_720.mp4")
					.storedInS3(false).description("something something").build();
			Movie movie8 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2019).title("ZOMBIELAND 2")
					.viewCount(274).likeCount(66).rating(5.2f).posterUrl("zombieland2.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie9 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2019).title("KNIVES OUT")
					.viewCount(277).likeCount(33).rating(5.3f).posterUrl("knivesout.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie10 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2018).title("THE HATE U GIVE")
					.viewCount(447).likeCount(64).rating(6.7f).posterUrl("hateugive.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie11 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2018)
					.title("AN EVENING WITH BEVERLY LUFF LINN").viewCount(74).likeCount(100).rating(3.5f)
					.posterUrl("aneveinglinn.jpg").regularStreamingUrl("sample_720.mp4").storedInS3(false)
					.description("something something").build();
			Movie movie12 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2018).title("GREEN BOOK")
					.viewCount(472).likeCount(333).rating(8.1f).posterUrl("greenbook.jpg")
					.regularStreamingUrl("sample_720.regularStreamingUrl").storedInS3(false)
					.description("something something").build();
			Movie movie13 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2018).title("SUMMER OF 84")
					.viewCount(278).likeCount(47).rating(9.0f).posterUrl("summerof84.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie14 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2017).title("JIGSAW")
					.viewCount(428).likeCount(100).rating(6.1f).posterUrl("jigsaw.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie15 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2017).title("DADDY'S HOME 2")
					.viewCount(457).likeCount(47).rating(3.6f).posterUrl("daddyshome2.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie16 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2017).title("GEOSTORM")
					.viewCount(278).likeCount(10).rating(1.5f).posterUrl("geostorm.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie17 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2017).title("BLADE RUNNER 2049")
					.viewCount(217).likeCount(25).rating(8.1f).posterUrl("bladerunner.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie18 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2017).title("IT").viewCount(663)
					.likeCount(274).rating(6.5f).posterUrl("It.jpg").regularStreamingUrl("sample_720.mp4")
					.storedInS3(false).description("something something").build();
			Movie movie19 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2016).title("DOCTOR STRANGE")
					.viewCount(555).likeCount(333).rating(2.4f).posterUrl("doctorstrange.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie20 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2016)
					.title("THE MAGNIFICENT SEVEN").viewCount(278).likeCount(113).rating(6.3f)
					.posterUrl("magnificentseven.jpg").regularStreamingUrl("sample_720.mp4").storedInS3(false)
					.description("something something").build();
			Movie movie21 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2016).title("SULLY")
					.viewCount(663).likeCount(124).rating(7.4f).posterUrl("sully.jpg")
					.regularStreamingUrl("sample_720.mp4").storedInS3(false).description("something something").build();
			Movie movie22 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2020)
					.title("SPENSER CONFIDENTIAL").viewCount(583).likeCount(144).rating(4.8f)
					.posterUrl("spenserconfidential.jpg").regularStreamingUrl("sample_720.mp4").storedInS3(false)
					.description("something something").build();
			Movie movie23 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2020)
					.title("THE LOVEBIRDS POSTER").viewCount(585).likeCount(146).rating(7.1f)
					.posterUrl("thelovebirds.jpg").regularStreamingUrl("sample_720.mp4").storedInS3(false)
					.description("something something").build();
			Movie movie24 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(2020)
					.title("THE LAST FULL MEASURE").viewCount(333).likeCount(163).rating(9.1f)
					.posterUrl("thelastfullmeasure.jpg").regularStreamingUrl("sample_720.mp4").storedInS3(false)
					.description("something something").build();

			movie1.setGenres(new HashSet<Genre>());
			movie2.setGenres(new HashSet<Genre>());

			Genre genre1 = Genre.builder().type(GenreType.horror).movie(movie1).build();
			Genre genre2 = Genre.builder().type(GenreType.action).movie(movie1).build();

			Genre genre3 = Genre.builder().type(GenreType.horror).movie(movie2).build();
			Genre genre4 = Genre.builder().type(GenreType.action).movie(movie2).build();

			movie1.getGenres().add(genre1);
			movie1.getGenres().add(genre2);

			movie2.getGenres().add(genre3);
			movie2.getGenres().add(genre4);

			movieRepository.save(movie1);
			movieRepository.save(movie2);
			movieRepository.save(movie3);
			movieRepository.save(movie4);
			movieRepository.save(movie5);
			movieRepository.save(movie6);
			movieRepository.save(movie7);
			movieRepository.save(movie8);
			movieRepository.save(movie9);
			movieRepository.save(movie10);
			movieRepository.save(movie11);
			movieRepository.save(movie12);
			movieRepository.save(movie13);
			movieRepository.save(movie14);
			movieRepository.save(movie15);
			movieRepository.save(movie16);
			movieRepository.save(movie17);
			movieRepository.save(movie18);
			movieRepository.save(movie19);
			movieRepository.save(movie20);
			movieRepository.save(movie21);
			movieRepository.save(movie22);
			movieRepository.save(movie23);
			movieRepository.save(movie24);

			Like like1 = Like.builder().movie(movie1).user(user1).build();
			likeRepository.save(like1);

			Comment comment1 = Comment.builder().body("Cool!").user(user1).movie(movie1).date(new Date()).build();
			Comment comment2 = Comment.builder().body("Nice").user(user1).movie(movie1).date(new Date()).build();

			commentRepository.save(comment1);
			commentRepository.save(comment2);

			WatchLater watchLater1 = new WatchLater();
			watchLater1.setMovies(new HashSet<Movie>());

			watchLater1.getMovies().add(movie1);
			watchLater1.setUser(user1);

			watchLaterRepository.save(watchLater1);
		};
	}

}
