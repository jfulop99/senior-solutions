package locations;

public class LocationParser {
    public Location parse(String text){
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Could not be blank");
        }
        String parts[] = text.split(",");
        if (parts.length != 3){
            throw new IllegalArgumentException("Must be 3 parts");
        }
        double lat = 0.0;
        double lon = 0.0;
        try {
            lat = Double.parseDouble(parts[1]);
            lon = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong coordinates");
        }
        return new Location(parts[0], lat, lon);
    }
}
