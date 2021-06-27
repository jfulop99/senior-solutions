package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong id = new AtomicLong();

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
        new Location(id.incrementAndGet(),"Bécs",45.497912,19.040235),
        new Location(id.incrementAndGet(),"Budapest",47.497912,19.040235),
        new Location(id.incrementAndGet(),"Prága",48.497912,19.040235))));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Location> getLocationsList(){
        return new ArrayList<>(locations);
    }

    public List<LocationDto> getLocations(Optional<String> prefix){

        return locations.stream()
                .filter(l -> prefix.isEmpty() || l.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        return modelMapper.map(locations, targetListType);
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(getLocationById(id), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(id.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = getLocationById(id);
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = getLocationById(id);
        locations.remove(location);
    }

    private Location getLocationById(long id){
        return locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Location not found: id = " + id));
    }

    public void deleteAllLocations() {
        id = new AtomicLong();
        locations.clear();
    }

}
