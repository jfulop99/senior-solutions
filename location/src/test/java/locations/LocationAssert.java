package locations;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class LocationAssert extends AbstractAssert<LocationAssert, Location> {

    public static LocationAssert assertThat(Location location) {
        return new LocationAssert(location);
    }

    public LocationAssert(Location location){
        super(location, LocationAssert.class);
    }

    public LocationAssert hasName(String name) {
        if (!Objects.equals(actual.getName(), name)) {
            failWithMessage("Expected name: " + name + " - Actual name: " + actual.getName());
        }
        return this;
    }
}
