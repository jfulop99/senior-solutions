package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations;

    public LocationsService() {
        locations = new ArrayList<>();
        locations.add(new Location("Bécs",45.497912,19.040235));
        locations.add(new Location("Budapest",47.497912,19.040235));
        locations.add(new Location("Prága",48.497912,19.040235));
    }

    public List<Location> getLocations(){
        return new ArrayList<>(locations);
    }
}
