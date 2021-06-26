package com.example.cars;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService = new CarService();

    @Test
    void getCars() {
        List<Car> cars = carService.getCars();
        assertEquals(4, cars.size());
        assertEquals("Audi", cars.get(1).getMake());
    }

    @Test
    void getTypes() {
        String result = carService.getTypes();
        assertEquals("Audi, Renault, VW", result);
    }
}