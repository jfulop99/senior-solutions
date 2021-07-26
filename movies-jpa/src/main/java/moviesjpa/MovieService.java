package moviesjpa;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    @Transactional
    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public MovieDTO createMovie(createMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Transactional
    public MovieDTO addRating(Long id, createRatingCommand command){

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find movie"));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDTO.class);
    }
}
