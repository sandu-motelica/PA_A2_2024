
package Homework.lab2;
/**
 * Clasa Depot reprezinta un depozit pentru vehicule.
 */
public class Depot {
    private String location;
    private Vehicle[] vehicles;

    /**
     * Construieste un obiect Depot cu locatia specificata.
     * @param location Locatia depozitului.
     */
    public Depot(String location) {
        this.location = location;
    }

    /**
     * Seteaza locatia depozitului.
     * @param location Noua locatie a depozitului.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Obtine locatia depozitului.
     * @return Locatia depozitului.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Seteaza vehiculele disponibile in depozit.
     * @param vehicles Lista de vehicule ce urmeaza a fi setate in depozit.
     */
    public void setVehicles(Vehicle... vehicles) {
        this.vehicles = vehicles;
        for(Vehicle v: vehicles){
            v.setDepot(this);
        }
    }

    /**
     * Obtine lista de vehicule din depozit.
     * @return Lista de vehicule din depozit.
     */
    public Vehicle[] getVehicles() {
        return vehicles;
    }

    /**
     * Returneaza o reprezentare text a obiectului Depot.
     * @return O string care descrie locatia depozitului.
     */
    @Override
    public String toString() {
        return "Depot{" +
                "location='" + location + '\'' +
                '}';
    }

    /**
     * Verifica daca obiectul Depot este egal cu un alt obiect.
     * @param obj Obiectul cu care se compara Depot-ul.
     * @return true daca obiectele sunt egale, false in caz contrar.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Depot))
            return false;
        Depot other = (Depot)obj;
        return location.equals(other.location);
    }
}
