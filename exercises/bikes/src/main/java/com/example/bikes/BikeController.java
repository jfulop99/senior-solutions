package com.example.bikes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeController {

    BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public List<BikeRent> history() {
        return bikeService.getAllRents();
    }

    @GetMapping("/users")
    public String users() {
        return bikeService.getUserIds();
    }
}
