package locations;

public enum Type {
    LAT(-90.0, 90.0), LON(-180.0, 180.0);

    private final double min;

    private final double max;

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    Type(double min, double max) {
        this.min = min;
        this.max = max;
    }



}
