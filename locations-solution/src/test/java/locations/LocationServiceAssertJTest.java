package locations;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceAssertJTest {

    @Test
    void readLocationsFromFileAssertJTest() throws URISyntaxException {

        // AssertJ

        List<Location> locations = readLocationsFromFile();

        assertThat(locations.size()).isEqualTo(5);
        assertThat(locations)
                .hasSize(5)
                .extracting(Location::getName)
                .contains("Budapest", "Budapest1", "Budapest2", "Budapest3", "Budapest4");

    }

    @Test
    void testWithMyAssert() throws URISyntaxException {

        List<Location> locations = readLocationsFromFile();

        LocationAssert.assertThat(locations.get(0)).hasName("Budapest");

    }

    private List<Location> readLocationsFromFile() throws URISyntaxException {
        URL fileUrl = getClass().getResource("/locations.csv");
        Path file = Path.of(fileUrl.toURI());
        return new LocationService().readLocationsFromFile(file);
    }

}
