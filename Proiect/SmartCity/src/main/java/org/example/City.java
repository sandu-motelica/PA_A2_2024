package org.example;

import java.util.List;
import java.util.ArrayList;

public class City {
    private String name;
    private List<Parking> parkings;

    public City(String name) {
        this.name = name;
        this.parkings = new ArrayList<>();
    }

    public void addParking(Parking parking) {
        this.parkings.add(parking);
    }

    public List<Parking> getParkings() {
        return this.parkings;
    }

    public String getName() {
        return name;
    }
}
