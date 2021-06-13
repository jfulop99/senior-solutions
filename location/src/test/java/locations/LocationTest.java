package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    @DisplayName("Setup")
    void setUp(){
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("Test for parse method")
    void testParse() {

        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest", location.getName());
        assertEquals(47.497912, location.getLat());
        assertEquals(19.040235, location.getLon());

    }

    @Test
    @DisplayName("Test for isOnEquator")
    void isOnEquatorTest() {
        Location location = locationParser.parse("Ecuador,0,19.040235");
        assertTrue(location.isOnEquator());
        location = locationParser.parse("Budapest,47.497912,19.040235");
        assertFalse(location.isOnEquator());
    }

    @Test
    @DisplayName("Test for isOnPrimeMeridian")
    void isOnPrimeMeridianTest() {
        Location location = locationParser.parse("Greenwich,47.497912,0");
        assertTrue(location.isOnPrimeMeridian());
        location = locationParser.parse("Budapest,47.497912,19.040235");
        assertFalse(location.isOnPrimeMeridian());
    }

    @Test
    @DisplayName("Different object with parser")
    void isDifferentObjectTest(){
        Location location1 = locationParser.parse("Budapest,47.497912,19.040235");
        Location location2 = locationParser.parse("Budapest,47.497912,19.040235");
        assertNotSame(location1, location2);
    }

    @Test
    void getDistanceFromTest() {
        Location location1 = locationParser.parse("Somewhere1,64.49835,-0.03203062");
        Location location2 = locationParser.parse("Somewhere1,24.55076,-63.50418");

        assertEquals(6297866.209, location1.getDistanceFrom(location2), 0.0005);
    }

    @Test
    @DisplayName("Exception when invalid latitude")
    void createWithInvalidLat(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Location("Test", 99, 13));
        assertEquals("Lat must be between -90 and 90", ex.getMessage());
    }

    @Test
    @DisplayName("Exception when invalid longitude")
    void createWithInvalidLon(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Location("Test", 47, 193));
        assertEquals("Lon must be between -180 and 180", ex.getMessage());
    }

    // Tesztesetek ismétlése

    private String[][] values = {{"Budapest,0,0", "true"}, {"Kecskemét,1,0", "false"}, {"Szeged,1,0", "false"}, {"Pécs,0,0", "true"}};

    @RepeatedTest(value = 4, name = "Test isOnEquator method {currentRepetition}/{totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repetitionInfo){
        assertEquals(Boolean.parseBoolean(values[repetitionInfo.getCurrentRepetition() - 1][1]),
                locationParser.parse(values[repetitionInfo.getCurrentRepetition() - 1][0]).isOnEquator());
    }

    // Paraméterezett tesztek

    @ParameterizedTest
    @MethodSource("createLocations")
    void testIsOnPrimeMeridian(String name, double lat, double lon, boolean isOnPrimeMeridian){
        Location location = new Location(name, lat, lon);
        assertEquals(isOnPrimeMeridian, location.isOnPrimeMeridian());
    }

    static Stream<Arguments> createLocations() {
        return Stream.of(
                arguments("Budapest", 0, 0, true),
                arguments("Kecskemét", 0, 15, false),
                arguments("Pécs", 15, 0, true),
                arguments("Szeged", 0, 45, false)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/distance.csv")
    void testDistanceFromOtherLocation(double lat1, double lon1, double lat2, double lon2, double distance){
        Location firstLocation = new Location("Loc1", lat1, lon1);
        Location secondLocation = new Location("Loc2", lat2, lon2);
        assertEquals(distance, firstLocation.getDistanceFrom(secondLocation), 0.0005);
    }

    // Dinamikus tesztek

    @TestFactory
    Stream<DynamicTest> areOnEquator() {
        return Stream.of(new Location("Budapest", 0, 0), new Location("Kecskemét", 0, 15),
                new Location("Pécs", 0, 35), new Location("Szeged", 0, 45))
                .map(location -> dynamicTest("Is on equator? " + location.getName(),
                        () -> assertTrue(location.isOnEquator())));
    }
}