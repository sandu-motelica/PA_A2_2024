package Homework.lab2;

/**
 * Clasa Truck reprezinta un tip specific de vehicul, camion.
 */
public class Truck extends Vehicle {
    private int capacity;
    /**
     * Construieste un obiect Truck cu un ID si o capacitate specificata.
     * @param id ID-ul camionului.
     * @param capacity Capacitatea de transport a camionului.
     */
    public Truck(String id, int capacity){
        super(id);
        this.capacity = capacity;
    }
    /**
     * Obtine capacitatea de transport a camionului.
     * @return Capacitatea de transport a camionului.
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * Seteaza capacitatea de transport a camionului.
     * @param capacity Noua capacitate de transport a camionului.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    /**
     * Returneaza o reprezentare text a obiectului Truck.
     * @return Un string care descrie ID-ul si capacitatea de transport a camionului, precum si depozitul la care este atribuit.
     */
    @Override
    public String toString() {
        return "Truck{" +
                "capacity=" + capacity +
                ", id='" + id + '\'' +
                ", depot=" + depot +
                '}';
    }
}
