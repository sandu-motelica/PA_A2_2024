package lab2;

public class Depot {
    public String location;

    public Depot(String location) {
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "location='" + location + '\'' +
                '}';
    }
}
