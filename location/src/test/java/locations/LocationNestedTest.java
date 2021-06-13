package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LocationNestedTest {

    LocationParser locationParser;

    @BeforeEach
    void setUp(){
        locationParser = new LocationParser();
    }

    @Nested
    class withOnEquator {
        Location location;
        @BeforeEach
        void setUp(){
            location = locationParser.parse("EcuadorGreenwich,0,0");
        }
        @Test
        void isOnEquatorTest(){
            assertTrue(location.isOnEquator());
        }
        @Test
        void isOnPrimeMeridianTest(){
            assertTrue(location.isOnPrimeMeridian());
        }
    }

    @Nested
    class withBudapest {
        Location location;
        @BeforeEach
        void setUp(){
            location = locationParser.parse("Budapest,47.497912,19.040235");
        }

        @Test
        void isOnEquatorTest(){
            assertFalse(location.isOnEquator());
        }

        @Test
        void isOnPrimeMeridianTest(){
            assertFalse(location.isOnPrimeMeridian());
        }
    }

}
