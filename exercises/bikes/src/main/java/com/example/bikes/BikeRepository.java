package com.example.bikes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BikeRepository {

    private List<BikeRent> bikeRentList;

    public BikeRepository() {
        bikeRentList = new ArrayList<>();
    }

    public List<BikeRent> getBikeRentList() {
        if (bikeRentList.isEmpty()) {
            loadDataFromFile("/training/senior-solutions/exercises/bikes/bikes.csv");
        }
        return new ArrayList<>(bikeRentList);
    }

    private void loadDataFromFile(String file) {
        Path path = Path.of(file);
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line;
            while ((line = reader.readLine()) != null) {
                bikeRentList.add(BikeRent.parse(line));
            }

        } catch (IOException e) {
            throw new IllegalStateException("Cannot read input file", e);
        }
    }
}
