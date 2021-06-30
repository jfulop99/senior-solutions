package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    MovieService movieService;

    @InjectMocks
    MovieController movieController;

    @Test
    void getMoviesWithoutParam() {
        when(movieService.getMovies(Optional.empty()))
                .thenReturn(List.of(
                        new MovieDto("Titanic", 180, 3.5),
                        new MovieDto("Spiderman", 120, 4.2),
                        new MovieDto("Superman", 99, 4.8)
                        ));
        List<MovieDto> movieDtoList = movieController.getMovies(Optional.empty());
        assertThat(movieDtoList)
                .hasSize(3)
                .extracting(MovieDto::getTitle)
                .contains("Titanic", "Spiderman", "Superman");
        verify(movieService).getMovies(Optional.empty());
    }

    @Test
    void getMoviesWithParam() {
        when(movieService.getMovies(Optional.of("s")))
                .thenReturn(List.of(
                        new MovieDto("Spiderman", 120, 4.2),
                        new MovieDto("Superman", 99, 4.8)
                        ));
        List<MovieDto> movieDtoList = movieController.getMovies(Optional.of("s"));
        assertThat(movieDtoList)
                .hasSize(2)
                .extracting(MovieDto::getTitle)
                .contains("Spiderman", "Superman");
        verify(movieService).getMovies(Optional.of("s"));
    }

    @Test
    void findMovieById() {
    }

    @Test
    void createMovie() {
    }

    @Test
    void deleteMovieById() {
    }

    @Test
    void ratingMovieById() {
    }

    @Test
    void getRateByMovieId() {
    }
}