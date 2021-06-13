package locations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationService {

    public void writeLocations(Path file, List<Location> locations){
        try (BufferedWriter writer = Files.newBufferedWriter(file)){
            for (Location item:locations) {
                writer.write(String.format(Locale.ROOT,"%s,%f,%f%n", item.getName(), item.getLat(), item.getLon()));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write file", e);
        }
    }

    public List<Location> readLocationsFromFile(Path file){
        LocationParser locationParser = new LocationParser();
        List<Location> locations = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)){
            String line;
            while ((line = reader.readLine()) != null){
                locations.add(locationParser.parse(line));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file", e);
        }
        return locations;
    }
}
