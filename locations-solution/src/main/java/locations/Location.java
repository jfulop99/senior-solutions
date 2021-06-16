package locations;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        this.name = name;
        if (lat < -90 || lat > 90){
            throw new IllegalArgumentException("Lat must be between -90 and 90");
        }
        this.lat = lat;
        if (lon < -180 || lon > 180){
            throw new IllegalArgumentException("Lon must be between -180 and 180");
        }
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isOnEquator(){
        return lat == 0;
    }

    public boolean isOnPrimeMeridian(){
        return lon == 0;
    }

    public double getDistanceFrom(Location location) {

        if (location == null) {
            throw new IllegalArgumentException("Location is null");
        }

        double lat1 = lat;
        double lat2 = location.getLat();
        double lon1 = lon;
        double lon2 = location.getLon();
        double el1 = 0D;
        double el2 = 0D;

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
