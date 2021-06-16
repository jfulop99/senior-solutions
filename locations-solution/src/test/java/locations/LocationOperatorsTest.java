package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    List<Location> locations;

    @BeforeEach
    void setUp() {
        locations = new ArrayList<>();
        locations.add(new Location("Loc1", 25, 13));
        locations.add(new Location("Loc2", -25, 13));
        locations.add(new Location("Loc3", 25, 13));
        locations.add(new Location("Loc4", 25, 13));
        locations.add(new Location("Loc5", 25, 13));
        locations.add(new Location("Loc6", -25, 13));
        locations.add(new Location("Loc7", 25, 13));
        locations.add(new Location("Loc8", 25, 13));
        locations.add(new Location("Loc9", -25, 13));
        locations.add(new Location("Loc10", 25, 13));
        locations.add(new Location("Loc11", 25, 13));
        locations.add(new Location("Loc12", -25, 13));
    }

    @Test
    void filterOnNorth() {
        List<Location> result = new LocationOperators().filterOnNorth(locations);

        assertEquals(8, result.size());
        assertEquals("Loc1", result.get(0).getName());
        assertEquals("Loc11", result.get(result.size()-1).getName());

    }
}