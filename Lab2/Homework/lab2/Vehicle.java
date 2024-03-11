package Homework.lab2;

/**
 * Clasa Vehicle reprezinta o clasa abstracta pentru vehicule.
 */
public abstract class Vehicle {
    protected String id;
    protected Depot depot;

    /**
     * Construieste un obiect Vehicle cu un ID specificat.
     * @param id ID-ul vehiculului.
     */
    public Vehicle(String id) {
        this.id = id;
    }

    /**
     * Seteaza depozitul asociat vehiculului.
     * @param depot Depozitul la care este atribuit vehiculul.
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }
    /**
     * Obtine depozitul asociat vehiculului.
     * @return Depozitul la care este atribuit vehiculul.
     */
    public Depot getDepot() {
        return depot;
    }
    /**
     * Seteaza ID-ul vehiculului.
     * @param id Noul ID al vehiculului.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Obtine ID-ul vehiculului.
     * @return ID-ul vehiculului.
     */
    public String getId() {
        return id;
    }

    /**
     * Verifica daca obiectul Vehicle este egal cu un alt obiect.
     * @param obj Obiectul cu care se compara Vehicle-ul.
     * @return true daca obiectele sunt egale, false in caz contrar.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Vehicle))
            return false;
        Vehicle other = (Vehicle)obj;
        return id.equals(other.id);
    }
}
