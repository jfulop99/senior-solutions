package com.example.cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void getCarsTest() {
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car("Audi", "A4", 3, Condition.NORMAL);
        car1.addKmState(new KmState(LocalDate.of(2018, 5, 1), 0));
        car1.addKmState(new KmState(LocalDate.of(2021, 1, 15), 65000));
        car1.addKmState(new KmState(LocalDate.of(2021, 6, 15), 68000));
        Car car2 = new Car("Audi", "A8", 3, Condition.EXCELLENT);
        car2.addKmState(new KmState(LocalDate.of(2018, 5, 1), 0));
        car2.addKmState(new KmState(LocalDate.of(2021, 1, 15), 65000));
        car2.addKmState(new KmState(LocalDate.of(2021, 6, 15), 68000));
        Car car3 = new Car("Renault", "Scenic", 7, Condition.NORMAL);
        car3.addKmState(new KmState(LocalDate.of(2013, 5, 1), 0));
        car3.addKmState(new KmState(LocalDate.of(2018, 1, 15), 65000));
        car3.addKmState(new KmState(LocalDate.of(2021, 6, 15), 150000));
        Car car4 = new Car("VW", "Golf", 13, Condition.BAD);
        car4.addKmState(new KmState(LocalDate.of(2008, 5, 1), 0));
        car4.addKmState(new KmState(LocalDate.of(2021, 1, 15), 165000));
        car4.addKmState(new KmState(LocalDate.of(2021, 6, 15), 168000));
        cars.add(car4);
        cars.add(car2);
        cars.add(car3);
        cars.add(car1);

        when(carService.getCars())
                .thenReturn(cars);

        assertThat(carController.getCars().size()).isEqualTo(4);
        assertThat(carController.getCars().get(2).getMake()).isEqualTo("Renault");
        verify(carService, times(2)).getCars();
    }

    @Test
    void getTypesTest() {
        when(carService.getTypes())
                .thenReturn("Audi, Renault, VW");
        assertThat(carController.getTypes()).isEqualTo("Audi, Renault, VW");
        verify(carService).getTypes();
    }
}