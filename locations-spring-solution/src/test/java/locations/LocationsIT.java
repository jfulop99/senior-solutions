package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsIT {

    @Autowired
    LocationsController locationsController;

    @Autowired
    LocationRepository locationRepository;

    @BeforeEach
    void setUp(){
        locationRepository.save(new Location("Bécs", 45.497912, 19.040235));
        locationRepository.save(new Location("Budapest", 47.497912, 19.040235));
        locationRepository.save(new Location("Prága", 48.497912, 19.040235));
    }

    @Test
    void getLocations(){
        List<LocationDto> location = locationsController.getLocation(Optional.empty());

        assertThat(location).hasSize(3)
        .extracting(LocationDto::getName)
        .contains("Bécs", "Budapest", "Prága");

    }

}
