package locations;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService distanceService;


    @Test
    void calculateDistanceWithTwoNamesTest() {
        when(locationRepository.findByName("Budapest"))
                .thenReturn(Optional.of(new Location("Budapest",47.497912,19.040235)));
        when(locationRepository.findByName("Pécs"))
                .thenReturn(Optional.of(new Location("Pécs",46.0707,18.2331)));
        assertThat(distanceService.calculateDistance("Budapest", "Pécs").get()).isEqualTo(170179.8489610379);
        verify(locationRepository).findByName("Budapest");
        verify(locationRepository).findByName("Pécs");
    }

    @Test
    void calculateDistanceWithNonExistNameTest() {
        when(locationRepository.findByName("Budapest"))
                .thenReturn(Optional.of(new Location("Budapest",47.497912,19.040235)));
        when(locationRepository.findByName("Nincs"))
                .thenReturn(Optional.empty());
        assertThat(distanceService.calculateDistance("Budapest", "Nincs")).isEqualTo(Optional.empty());
        verify(locationRepository).findByName("Budapest");
        verify(locationRepository).findByName("Nincs");
    }
}