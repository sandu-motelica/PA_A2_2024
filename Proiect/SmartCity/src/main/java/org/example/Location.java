package org.example;

public class Location {
    private int locationId;
    private String name;
    private double x;
    private double y;

    public Location(int id, String name, double x, double y) {
        this.locationId = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Location(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Location other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return locationId;
    }

    public void setId(int id) {
        this.locationId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return this.getX()==((Location) o).getX() && this.getY() == ((Location) o).getY();
    }
}
