package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public String getLocations(){

        return LocalDateTime.now().toString() + "<br />" +
                locationsService.getLocations()
                .stream()
                .map(Location::toString).collect(Collectors.joining("<br />"));
    }

}
