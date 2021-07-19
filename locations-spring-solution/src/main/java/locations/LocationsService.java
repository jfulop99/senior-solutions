package locations;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.capitalize;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(LocationProperties.class)
@Slf4j
public class LocationsService {

//    private final LocationDao locationDao;
    private final LocationRepository locationRepository;

    private final ModelMapper modelMapper;

    private final LocationProperties locationProperties;

//    private static final Logger log = LoggerFactory.getLogger(LocationsService.class);

    private AtomicLong id = new AtomicLong();

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
        new Location(id.incrementAndGet(),"Bécs",45.497912,19.040235),
        new Location(id.incrementAndGet(),"Budapest",47.497912,19.040235),
        new Location(id.incrementAndGet(),"Prága",48.497912,19.040235))));

    public List<Location> getLocationsList(){
        return new ArrayList<>(locations);
    }

    public List<LocationDto> getLocations(Optional<String> prefix){

        String name = "%" + prefix.orElse("") + "%";

        return locationRepository.findAllByName(name).stream()
//                .filter(l -> prefix.isEmpty() || l.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());

//        return locations.stream()
//                .filter(l -> prefix.isEmpty() || l.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
//                .map(location -> modelMapper.map(location, LocationDto.class))
//                .collect(Collectors.toList());
//        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
//        return modelMapper.map(locations, targetListType);
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(getLocationById(id), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {

        String name = locationProperties.getUppercase()? capitalize(command.getName()) : command.getName();

//        Location location = new Location(id.incrementAndGet(), name, command.getLat(), command.getLon());
//        locations.add(location);

        Location location = locationRepository.save(new Location(name, command.getLat(), command.getLon()));

        log.info("Location has been created with id = {}", id);
        log.debug("Location has been created with name {} id = {}", name, id);
        return modelMapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {

        String name = locationProperties.getUppercase() ? capitalize(command.getName()) : command.getName();

//        Location location = getLocationById(id);
//        location.setName(name);
//        location.setLat(command.getLat());
//        location.setLon(command.getLon());

//        Location location = new Location(id, name, command.getLat(), command.getLon());

        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Location not found"));

        location.setName(name);
        location.setLon(command.getLon());
        location.setLat(command.getLat());
//        locationDao.updateLocation(location);

        log.info("Location has been updated with id = {}", id);
        log.debug("Location has been updated with name {} id = {}", name, id);

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
//        Location location = getLocationById(id);
//        locations.remove(location);
        locationRepository.deleteById(id);
    }

    public void deleteAllLocations() {
//        id = new AtomicLong();
//        locations.clear();
        locationRepository.deleteAll();
    }

    private Location getLocationById(long id){
        return locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Location not found"));
//        return locations.stream()
//                .filter(l -> l.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new LocationNotFoundException("Location not found: id = " + id));
    }
}
