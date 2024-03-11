package Homework;

import Homework.lab2.*;

import java.time.LocalTime;

/**
 * Clasa principala unde sunt create obiectele si sunt rulate problemele
 * @author MotelicaSandu
 */

public class Homework {
    public static void main(String[] args) {
        Client client1 = new Client("Ion", ClientType.PREMIUM, LocalTime.NOON, LocalTime.parse("20:00"));
        Client client2 = new Client("Alexandru", ClientType.REGULAR, LocalTime.MIDNIGHT, LocalTime.NOON);
        Client client3 = new Client("Andrei", ClientType.REGULAR, LocalTime.parse("15:00"), LocalTime.parse("23:50"));
        Client client4 = new Client("Ioana", ClientType.PREMIUM, LocalTime.NOON, LocalTime.parse("22:00"));
        Depot depot1 = new Depot("Copou");
        Depot depot2 = new Depot("Dacia");
        Truck truck1 = new Truck("ISX153", 7);
        Truck truck2 = new Truck("HGF22",4);
        Drone drone1 = new Drone("JGX122", 6);
        Drone drone2 = new Drone("AAA644", 7);
        depot1.setVehicles(truck1, drone1,drone2);
        depot2.setVehicles(truck2);
        Depot[] depots = {depot1, depot2};
        Client[] clients = {client1, client2,client3,client4};
        Problem p = new Problem(depots, clients);
        Tour[] tours = p.greedyAllocateClients(LocalTime.parse("07:00"));
        System.out.println("Results:");
        for(Tour t: tours) {
            System.out.println(t);
        }
    }
}
