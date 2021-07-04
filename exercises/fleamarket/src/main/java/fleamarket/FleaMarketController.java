package fleamarket;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fleamarket/advertisement")
public class FleaMarketController {

    private FleaMarketService fleaMarketService;

    public FleaMarketController(FleaMarketService fleaMarketService) {
        this.fleaMarketService = fleaMarketService;
    }

    @DeleteMapping("all")
    public void deleteAll() {
        fleaMarketService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        fleaMarketService.deleteById(id);
    }

    @DeleteMapping
    public void deleteOldestByCategory(@RequestParam Optional<String> category){
        fleaMarketService.deleteOldestByCategory(category);
    }

    @GetMapping
    public List<AdvertisementDTO> getAdvertisements(@RequestParam Optional<String> category, Optional<String> word){
        return fleaMarketService.getAdvertisements(category, word);
    }

    @GetMapping("/{id}")
    public AdvertisementDTO getAdvertisementById(@PathVariable long id){
        return fleaMarketService.getAdvertisementById(id);
    }

    @PostMapping
    public AdvertisementDTO createAdvertisement(@Valid @RequestBody CreateAdvertisementCommand command){
        return fleaMarketService.createAdvertisement(command);
    }

    @PutMapping("/{id}")
    public AdvertisementDTO updateAdvertisement(@PathVariable long id, @Valid @RequestBody UpdateAdvertisementCommand command){
        return fleaMarketService.updateAdvertisement(id, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException e) {

        Problem problem = Problem.builder()
                .withType(URI.create("advertisement/not-found"))
                .withTitle("Not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
