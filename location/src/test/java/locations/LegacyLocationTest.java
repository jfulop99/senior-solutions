package locations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LegacyLocationTest {

    @Test
    public void testLocationParse(){
        Location location = new LocationParser().parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest", location.getName());
    }
}
