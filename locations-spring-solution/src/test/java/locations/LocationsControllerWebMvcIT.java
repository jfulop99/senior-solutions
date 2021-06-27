package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


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

}
