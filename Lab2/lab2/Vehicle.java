package lab2;

public class Vehicle {
    private String id;
    private int capacity;
    private Depot depot;

    public Vehicle(String id, int capacity, Depot depot) {
        this.id = id;
        this.capacity = capacity;
        this.depot = depot;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getid() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", depot=" + depot +
                '}';
    }
}
