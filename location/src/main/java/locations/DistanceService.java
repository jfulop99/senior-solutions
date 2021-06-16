package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {

        if (isEmpty(name1) || isEmpty(name2)) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        Optional<Location> firstLocation = locationRepository.findByName(name1);
        Optional<Location> secondLocation = locationRepository.findByName(name2);

        if (firstLocation.isEmpty() || secondLocation.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(firstLocation.get().getDistanceFrom(secondLocation.get()));
    }

    private boolean isEmpty(String name) {
        return (name == null) || name.isBlank();
    }

}
