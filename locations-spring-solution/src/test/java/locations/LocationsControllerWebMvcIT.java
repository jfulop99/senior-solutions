package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    AtomicLong id = new AtomicLong();

    @Test
    void testGetLocations() throws Exception {
        when(locationsService.getLocations(any()))
                .thenReturn(new ArrayList<>(List.of(
                        new LocationDto(id.incrementAndGet(),"Bécs",45.497912,19.040235),
                        new LocationDto(id.incrementAndGet(),"Budapest",47.497912,19.040235),
                        new LocationDto(id.incrementAndGet(),"Prága",48.497912,19.040235)
                )));

        mockMvc.perform(get("/locations"))
//                .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Bécs")));

    }

    @Test
    void findLocationById() throws Exception {
        when(locationsService.findLocationById(1))
                .thenReturn(new LocationDto(id.incrementAndGet(), "Bécs", 45.497912, 19.040235));

        mockMvc.perform(get("/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("Bécs")));
    }
        @Test
        void findLocationByIdNotFound() throws Exception {
        when(locationsService.findLocationById(6))
                .thenThrow(new LocationNotFoundException("Location not found: id = 6"));

        mockMvc.perform(get("/locations/6"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("detail", equalTo("Location not found: id = 6")))
                .andExpect(jsonPath("type", equalTo("location/not-found")));

    }

    @Test
    void createLocation() {
    }

    @Test
    void updateLocation() {
    }

    @Test
    void deleteLocation() {
    }
}
