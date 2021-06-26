package com.example.bikes;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BikeRepositoryTest {

    @Test
    void getBikeRentListTest() {
        BikeRepository bikeRepository = new BikeRepository();

        List<BikeRent> bikeRentList = bikeRepository.getBikeRentList();

        assertEquals(5, bikeRentList.size());
        assertEquals("FH675", bikeRentList.get(0).getBikeId());
        assertEquals("FH631", bikeRentList.get(4).getBikeId());
    }
}