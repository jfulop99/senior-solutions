package com.example.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;

    @BeforeEach
    void setUp() {

        car1 = new Car("Audi", "A4", 3, Condition.NORMAL);
        car1.addKmState(new KmState(LocalDate.of(2018, 5, 1), 0));
        car1.addKmState(new KmState(LocalDate.of(2021, 1, 15), 65000));
        car1.addKmState(new KmState(LocalDate.of(2021, 6, 15), 68000));
        car2 = new Car("Audi", "A8", 3, Condition.EXCELLENT);
        car2.addKmState(new KmState(LocalDate.of(2018, 5, 1), 0));
        car2.addKmState(new KmState(LocalDate.of(2021, 1, 15), 65000));
        car2.addKmState(new KmState(LocalDate.of(2021, 6, 15), 68000));
        car3 = new Car("Renault", "Scenic", 7, Condition.NORMAL);
        car3.addKmState(new KmState(LocalDate.of(2013, 5, 1), 0));
        car3.addKmState(new KmState(LocalDate.of(2018, 1, 15), 65000));
        car3.addKmState(new KmState(LocalDate.of(2021, 6, 15), 150000));
        car4 = new Car("VW", "Golf", 13, Condition.BAD);
        car4.addKmState(new KmState(LocalDate.of(2008, 5, 1), 0));
        car4.addKmState(new KmState(LocalDate.of(2021, 1, 15), 165000));
        car4.addKmState(new KmState(LocalDate.of(2021, 6, 15), 168000));

    }

    @Test
    void CreateTest() {
        assertEquals("Audi", car1.getMake());
        assertEquals("A4", car1.getModel());
        assertEquals(Condition.NORMAL, car1.getCondition());
        assertEquals(0, car1.getKmStates().get(0).getActualKm());
    }

    @Test
    void addKmStateTest() {

        car4.addKmState(new KmState(LocalDate.of(2021, 6, 25), 168500));

        assertEquals(168500, car4.getKmStates().get(car4.getKmStates().size() - 1).getActualKm());

    }
}