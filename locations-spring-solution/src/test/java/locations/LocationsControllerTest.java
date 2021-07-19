package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocationsListTest() {
        when(locationsService.getLocationsList())
                .thenReturn(List.of(
                        new Location("Bécs",45.497912,19.040235),
                        new Location("Budapest",47.497912,19.040235),
                        new Location("Prága",48.497912,19.040235)));

        assertThat(locationsController.getLocationsList()).endsWith("Location(id=null, name=Prága, lat=48.497912, lon=19.040235)");
    }
}