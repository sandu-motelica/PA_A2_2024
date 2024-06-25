package org.example;
public class Route {
    private int locationId1;
    private int locationId2;

    public Route(int locationId1, int locationId2) {
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
    }

    public int getLocationId1() {
        return locationId1;
    }

    public int getLocationId2() {
        return locationId2;
    }
}

