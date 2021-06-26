package com.example.cars;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = new ArrayList<>();

    public CarService() {
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
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getTypes() {
        return cars.stream()
                .map(Car::getMake)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }
}
