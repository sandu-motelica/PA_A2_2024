package org.example;

public class Driver {
    private String name;

    public Driver(String name) {
        this.name = name;
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
}
