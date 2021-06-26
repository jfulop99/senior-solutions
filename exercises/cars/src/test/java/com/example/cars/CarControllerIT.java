package com.example.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarService carService;

    @Autowired
    CarController carController;

    @Test
    void carControllerGetTypesTest() {
        assertEquals("Audi, Renault, VW", carController.getTypes());
    }

    @Test
    void carControllerGetCarsTest() {
        List<Car> cars = carController.getCars();
        assertEquals(4, cars.size());
        assertEquals("Renault", cars.get(2).getMake());
    }
}
