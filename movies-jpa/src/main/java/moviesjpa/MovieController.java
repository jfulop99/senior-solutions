package moviesjpa;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> getMovies(){
        return movieService.findAll();
    }

    @PostMapping
    public MovieDTO saveMovie(@RequestBody createMovieCommand command){
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}")
    public MovieDTO addRating(@PathVariable Long id, @RequestBody createRatingCommand command){
        return movieService.addRating(id, command);
    }
}
