package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void createLocationTest(){
        Location location = new Location(1L, "Budapest", 47.1312, 19.2322);

        assertEquals(1L, location.getId());
        assertEquals("Budapest", location.getName());
    }


}