package com.example.cars;

import java.time.LocalDate;

public class KmState {
    final private LocalDate date;
    final private int actualKm;

    public KmState(LocalDate date, int actualKm) {
        this.date = date;
        this.actualKm = actualKm;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getActualKm() {
        return actualKm;
    }
}
