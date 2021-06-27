package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@Tag(name = "Operations on locations")
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locationslist")
    public String getLocationsList(){

        return LocalDateTime.now().toString() + " devtools 3 <br />" +
                locationsService.getLocationsList()
                .stream()
                .map(Location::toString).collect(Collectors.joining("<br />"));
    }

    @GetMapping
    public List<LocationDto> getLocation(
            @RequestParam Optional<String> prefix
            ){
        return locationsService.getLocations(prefix);
    }

    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable long id){
        return locationsService.findLocationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a location")
    @ApiResponse(responseCode = "201", description = "Location has been created")
    public LocationDto createLocation(@RequestBody CreateLocationCommand command){
        return locationsService.createLocation(command);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command){
        return locationsService.updateLocation(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") long id){
        locationsService.deleteLocation(id);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(LocationNotFoundException e) {

        Problem problem = Problem.builder()
                .withType(URI.create("location/not-found"))
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
