package Homework.lab2;

/**
 * Clasa Drone reprezinta un tip specific de vehicul, drona.
 */
public class Drone extends Vehicle {
    private int duration;
    /**
     * Construieste un obiect Drone cu un ID si o durata specificata.
     * @param id ID-ul dronei.
     * @param duration Durata de zbor a dronei.
     */
    public Drone(String id, int duration){
        super(id);
        this.duration = duration;
    }
    /**
     * Obtine durata de zbor a dronei.
     * @return Durata de zbor a dronei.
     */
    public int getDuration() {
        return duration;
    }
    /**
     * Seteaza durata de zbor a dronei.
     * @param duration Noua durata de zbor a dronei.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * Returneaza o reprezentare text a obiectului Drone.
     * @return Un string care descrie ID-ul si durata de zbor a dronei, precum si depozitul la care este atribuita.
     */
    @Override
    public String toString() {
        return "Drone{" +
                "duration=" + duration +
                ", id='" + id + '\'' +
                ", depot=" + depot +
                '}';
    }
}
