package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LocationRepositoryH2IT {

    @Autowired
    LocationRepository locationRepository;

    @Test
    void saveTest(){
        locationRepository.save(new Location("Bécs", 45.497912, 19.040235));
        locationRepository.save(new Location("Budapest", 47.497912, 19.040235));
        locationRepository.save(new Location("Prága", 48.497912, 19.040235));

        List<Location> locations = locationRepository.findAll();

        assertThat(locations)
                .hasSize(3)
                .extracting(Location::getName)
                .contains("Bécs", "Budapest", "Prága");

    }

    @Test
    void deleteTest(){
        locationRepository.save(new Location("Bécs", 45.497912, 19.040235));
        Location location = locationRepository.save(new Location("Budapest", 47.497912, 19.040235));
        Long id = location.getId();

        locationRepository.delete(locationRepository.findById(id).orElseThrow());

        List<Location> locations = locationRepository.findAll();

        assertThat(locations)
                .hasSize(1)
                .extracting(Location::getName)
                .contains("Bécs");
    }

    @Test
    void updateTest(){
        Location location = locationRepository.save(new Location("Budapest", 47.497912, 19.040235));
        Long id = location.getId();

        Location otherLocation = locationRepository.findById(id).orElseThrow();

        otherLocation.setName("Bécsújhely");

        locationRepository.save(otherLocation);

        Location updatedLocation = locationRepository.findById(id).orElseThrow();

        assertEquals("Bécsújhely", updatedLocation.getName());
    }

    @Test
    void findNameLikeTest(){
        locationRepository.save(new Location("Bécs", 45.497912, 19.040235));
        locationRepository.save(new Location("Budapest", 47.497912, 19.040235));
        locationRepository.save(new Location("Prága", 48.497912, 19.040235));

        List<Location> locations = locationRepository.findAllByNameLike("B%");

        assertThat(locations)
                .hasSize(2)
                .extracting(Location::getName)
                .contains("Bécs", "Budapest");

    }


}
