package Homework.lab2;

import java.time.LocalTime;

/**
 * Incapsuleaza datele reprezentative clientilor
 * @author Sandu
 */
public class Client {
    private String name;
    private ClientType type;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Construieste un obiect Client cu numele, tipul, timpul de inceput si timpul de sfarsit specificate.
     *
     * @param name Numele clientului.
     * @param type Tipul clientului.
     * @param startTime Timpul de inceput al perioadei de servire a clientului.
     * @param endTime Timpul de sfarsit al perioadei de servire a clientului.
     */
    public Client(String name, ClientType type, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Returneaza numele clientului.
     *
     * @return Numele clientului.
     */
    public String getName() {
        return name;
    }

    /**
     * Seteaza numele clientului.
     *
     * @param name Noul nume al clientului.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returneaza tipul clientului.
     *
     * @return Tipul clientului.
     */
    public ClientType getType() {
        return type;
    }

    /**
     * Seteaza tipul clientului.
     *
     * @param type Noul tip al clientului.
     */
    public void setType(ClientType type) {
        this.type = type;
    }

    /**
     * Returneaza timpul de sfarsit al clientului.
     *
     * @return Timpul de sfarsit al clientului.
     */
    public LocalTime getEndTime() {
        return endTime;
    }


    /**
     * Seteaza timpul de inceput al intervalului clientului.
     *
     * @param startTime Noul timp de inceput al clientului.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returneaza timpul de inceput al clientului.
     *
     * @return Timpul de inceput al clientului.
     */
    public LocalTime getStartTime() {
        return startTime;
    }
    /**
     * Seteaza timpul de sfarsit al intervalului clientului.
     *
     * @param endTime Noul timp de sfarsit al clientului.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Client))
            return false;
        Client other = (Client)obj;
        return name.equals(other.name);
    }
}
