package movies;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    private AtomicLong id = new AtomicLong();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getMovies(Optional<String> prefix) {

            return movies.stream()
                    .filter(movie -> prefix.isEmpty() || movie.getTitle().toLowerCase().startsWith(prefix.get().toLowerCase()))
                    .map(movie -> modelMapper.map(movie,MovieDto.class))
                    .collect(Collectors.toList());
    }


    public MovieDto createMovie(CreateMovieCommand command) {

        Movie movie = new Movie(id.incrementAndGet(), command.getTitle(), command.getPlayingTime());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto findMovieById(long id) {
        return modelMapper.map(getMovieById(id), MovieDto.class);

    }

    private Movie getMovieById(long id){
        return movies.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location not found: id = " + id));
    }

    public void deleteMovieById(long id) {
        Movie movie = getMovieById(id);
        movies.remove(movie);
    }

    public MovieDto ratingMovieById(long id, Optional<Integer> rate) {

        Movie movie = getMovieById(id);
        rate.ifPresent(movie::addRating);
        return modelMapper.map(movie, MovieDto.class);

    }

    public MovieDto getRateByMovieId(long id) {
        Movie movie = getMovieById(id);

        return modelMapper.map(movie, MovieDto.class);
    }
}
