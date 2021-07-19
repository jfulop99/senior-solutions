package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {


    @Test
    void getLocationsTest() {
        LocationsService locationsService = new LocationsService(null, null, null);

        String message = locationsService.getLocationsList().toString();

        assertThat(message).startsWith("[Location(id=");
    }
}