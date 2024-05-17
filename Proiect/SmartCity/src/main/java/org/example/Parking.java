package org.example;
import java.util.List;
import java.util.ArrayList;

public class Parking {
    private String address;
    private List<ParkingSpot> spots;

    public Parking(String address, int numberOfSpots) {
        this.address = address;
        this.spots = new ArrayList<>();
        for (int i = 0; i < numberOfSpots; i++) {
            spots.add(new ParkingSpot(i + 1));
        }
    }

    public String getAddress() {
        return address;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public boolean isFull() {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                return false;
            }
        }
        return true;
    }
}

