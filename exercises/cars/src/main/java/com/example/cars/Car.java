package com.example.cars;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private final String make;
    private final String model;
    private int age;
    private Condition condition;
    private List<KmState> kmStates;

    public Car(String make, String model, int age, Condition condition) {
        this.make = make;
        this.model = model;
        this.age = age;
        this.condition = condition;
        kmStates = new ArrayList<>();
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getAge() {
        return age;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<KmState> getKmStates() {
        return new ArrayList<>(kmStates);
    }

    public void addKmState(KmState kmState) {
        kmStates.add(kmState);
    }
}
