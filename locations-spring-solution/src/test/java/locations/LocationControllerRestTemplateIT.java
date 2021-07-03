package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @RepeatedTest(value = 2)
    void testGetLocations() {

        locationsService.deleteAllLocations();

        LocationDto locationDto =
                template.postForObject("/locations", new CreateLocationCommand("Kukutyin", 54.234567, 19.123456), LocationDto.class);
        assertEquals("Kukutyin", locationDto.getName());

        template.postForObject("/locations", new CreateLocationCommand("Tengelic", 45.234567, 17.123456), LocationDto.class);

        List<LocationDto> locations = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();

        assertThat(locations)
                .extracting(LocationDto::getName)
                .contains("Kukutyin", "Tengelic");
    }


}
