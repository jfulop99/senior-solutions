package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations(){
        String message = locationsController.getLocations();

        assertThat(message).startsWith(LocalDate.now().toString());

        assertThat(message).endsWith("Location{id=null, name='Prága', lat=48.497912, lon=19.040235}");
    }

}
