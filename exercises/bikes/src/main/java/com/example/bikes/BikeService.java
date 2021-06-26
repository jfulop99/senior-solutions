package com.example.bikes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<BikeRent> getAllRents() {
        return bikeRepository.getBikeRentList();
    }

    public String getUserIds() {
        return bikeRepository.getBikeRentList()
                .stream()
                .map(BikeRent::getUserId)
                .distinct()
                .collect(Collectors.joining(", "));
    }

}
