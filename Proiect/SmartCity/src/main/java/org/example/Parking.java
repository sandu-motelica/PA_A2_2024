package org.example;
import java.util.List;
import java.util.ArrayList;

public class Parking extends Location{
    private String address;
    private int totalSpots;
    private int occupiedSpots;
    private List<ParkingSpot> spots;


    public Parking(int id, String name, String address, int numberOfSpots, int occSpots, double x, double y) {
        super(id, name, x, y);
        this.address = address;
        this.totalSpots = numberOfSpots;
        this.occupiedSpots = occSpots;
        this.spots = new ArrayList<>();
        for (int i = 0; i < numberOfSpots; i++) {
            spots.add(new ParkingSpot(i + 1));
        }
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

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public int getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(int occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }
}

