package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    List<Location> locations = new ArrayList<>();


    @TempDir
    Path tempDir;

    @Test
    void writeLocationsTest() throws IOException {
        locations.add(new Location("Bécs",45.497912,19.040235));
        locations.add(new Location("Budapest",47.497912,19.040235));
        locations.add(new Location("Prága",48.497912,19.040235));
        Path file = tempDir.resolve("locations.txt");
        new LocationService().writeLocations(file, locations);
        List<String> lines = Files.readAllLines(file);
        assertEquals("Budapest,47.497912,19.040235", lines.get(1));
    }

    @Test
    void readLocationsFromFileTest() throws URISyntaxException {
        URL fileUrl = getClass().getResource("/locations.csv");
        Path file = Path.of(fileUrl.toURI());
        List<Location> locations = new LocationService().readLocationsFromFile(file);

        assertThat(locations.size(), equalTo(5));
        assertThat(locations, hasItem(hasProperty("name", equalTo("Budapest"))));
        assertThat(locations, hasItem(hasProperty("name", equalTo("Budapest4"))));
    }
}