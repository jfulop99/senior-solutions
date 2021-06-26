package com.example.bikes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BikeRent {

    private String bikeId;
    private String userId;
    private LocalDateTime lastDropTime;
    private double lastTrip;

    public BikeRent(String bikeId, String userId, LocalDateTime lastDropTime, double lastTrip) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.lastDropTime = lastDropTime;
        this.lastTrip = lastTrip;
    }

    public static BikeRent parse(String dataLine) {
        String[] parts = dataLine.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BikeRent bikeRent = new BikeRent(parts[0], parts[1], LocalDateTime.parse(parts[2], formatter), Double.parseDouble(parts[3]));
        return bikeRent;
    }

    public String getBikeId() {
        return bikeId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getLastDropTime() {
        return lastDropTime;
    }

    public double getLastTrip() {
        return lastTrip;
    }
}
