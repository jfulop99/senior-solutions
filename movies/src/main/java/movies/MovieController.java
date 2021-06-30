package movies;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies(
            @RequestParam Optional<String> prefix
    ){
        return movieService.getMovies(prefix);
    }

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable long id){
        return movieService.findMovieById(id);
    }


    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable long id) {
        movieService.deleteMovieById(id);
    }

    @PostMapping("/{id}/rating")
    public MovieDto ratingMovieById(@PathVariable long id, @RequestParam Optional<Integer> rate) {
        return movieService.ratingMovieById(id, rate);

    }


    @GetMapping("/{id}/rating")
    public MovieDto getRateByMovieId(@PathVariable long id) {
        return movieService.getRateByMovieId(id);

    }

}
