package org.example;

public class Driver {
    private int id;
    private String name;
    private int locationId;

    public Driver(int id, String name, int locationId) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public boolean park(Parking parkingLot) {
        for (ParkingSpot spot : parkingLot.getSpots()) {
            if (!spot.isOccupied()) {
                spot.occupy();
                return true;
            }
        }
        return false;
    }

    public void leave(Parking parkingLot, int spotNumber) {
        for (ParkingSpot spot : parkingLot.getSpots()) {
            if (spot.getSpotNumber() == spotNumber) {
                spot.vacate();
                break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
