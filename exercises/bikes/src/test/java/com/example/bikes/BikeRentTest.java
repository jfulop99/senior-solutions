package com.example.bikes;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BikeRentTest {

    @Test
    void parseTest() {
        BikeRent bikeRent = BikeRent.parse("FH675;US3434;2021-06-24 17:12:50;0.8");
        assertEquals("FH675", bikeRent.getBikeId());
        assertEquals("US3434", bikeRent.getUserId());
        assertEquals( LocalDateTime.of(2021, 6, 24, 17, 12, 50), bikeRent.getLastDropTime());
        assertEquals(0.8D, bikeRent.getLastTrip());
    }

    @Test
    void createTest() {
        BikeRent bikeRent = new BikeRent("FH675", "US3434",
                LocalDateTime.of(2021, 6, 24, 17, 12, 50), 0.8);
        assertEquals("FH675", bikeRent.getBikeId());
        assertEquals("US3434", bikeRent.getUserId());
        assertEquals( LocalDateTime.of(2021, 6, 24, 17, 12, 50), bikeRent.getLastDropTime());
        assertEquals(0.8D, bikeRent.getLastTrip());
    }
}