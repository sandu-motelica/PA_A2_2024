package Homework.lab2;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Clasa Problem reprezinta o problema de optimizare ce contine depozite si clienti.
 * Sunt furnizate metode pentru manipularea informatiilor despre depozite, clienti, vehicule si tururi,
 * precum si functionalitatea de deservire a clientilor folosind un algoritm greedy.
 */
public class Problem {
    private Depot[] depots;
    private Client[] clients;

    /**
     * Construieste un obiect Problem cu array-urile date de depozite si clienti.
     * Verifica duplicate de depozite si clienti, afisand un mesaj de eroare si
     * iesind din program in cazul in care se gasesc duplicate.
     *
     * @param depots depozitele din problema.
     * @param clients  clientii din problema.
     */
    public Problem(Depot[] depots, Client[] clients) {
        for (int i = 0; i < depots.length - 1; i++) {
            for (int j = i + 1; j < depots.length; j++) {
                if (depots[i].equals(depots[j])) {
                    System.out.println("O problema nu poate contine doua depozite identice");
                    System.exit(-1);
                }
            }
        }
        this.depots = depots;
        for (int i = 0; i < clients.length - 1; i++) {
            for (int j = i + 1; j < clients.length; j++) {
                if (clients[i].equals(clients[j])) {
                    System.out.println("O problema nu poate contine doi clienti identici");
                    System.exit(-1);
                }
            }
        }
        this.clients = clients;
    }

    /**
     * Obtine toate vehiculele disponibile in depozite.
     *
     * @return Un array de obiecte Vehicle care contine toate vehiculele disponibile in depozite.
     */
    public Vehicle[] getVehicles() {
        int totalVehicles = 0;
        for (Depot d : depots) {
            totalVehicles += d.getVehicles().length;
        }
        Vehicle[] allVehicles = new Vehicle[totalVehicles];
        int index = 0;
        for (Depot d : depots) {
            Vehicle[] depotVehicles = d.getVehicles();
            for (Vehicle v : depotVehicles) {
                allVehicles[index++] = v;
            }
        }
        return allVehicles;
    }

    /**
     *  Aloca la vehicule clientii ce vor fi serviti folosind un algoritm greedy bazat pe timpuri de calatorie aleatorii.
     *
     * @param currentTime timpul curent folosit pentru calcularea timpilor de calatorie.
     * @return Un array de obiecte Tour care reprezinta cursele efectuate de fiecare vehicul.
     */
    public Tour[] greedyAllocateClients(LocalTime currentTime) {
        Vehicle[] tempVehicles = this.getVehicles();
        int numberOfVehicles = tempVehicles.length;
        Tour[] resultsTours = new Tour[numberOfVehicles];
        for (int i = 0; i < numberOfVehicles; i++) {
            resultsTours[i] = new Tour();
            resultsTours[i].setVehicle(tempVehicles[i]);
        }
        sortClientsByStartTime();
        for (int i = 0; i < clients.length; i++) {
//            System.out.println("client" + clients[i]);
            int[] randomTravelTimes = new int[numberOfVehicles];
            for (int j = 0; j < numberOfVehicles; j++) {
                randomTravelTimes[j] = (int) (Math.random() * 700 + 5);
            }
            int bestTourIndex = minTime(randomTravelTimes, i, currentTime);
            if (bestTourIndex != -1)
                resultsTours[bestTourIndex].addClient(clients[i]);
        }
        return resultsTours;
    }

    /**
     * Obtine depozitele din problema.
     *
     * @return Un array de obiecte Depot care reprezinta depozitele din problema.
     */
    public Depot[] getDepots() {
        return depots;
    }

    /**
     * Seteaza depozitele pentru problema.
     *
     * @param depots Un array de obiecte Depot care reprezinta depozitele ce urmeaza a fi setate.
     */
    public void setDepots(Depot[] depots) {
        this.depots = depots;
    }

    /**
     * Obtine clientii din problema.
     *
     * @return Un array de obiecte Client care reprezinta clientii din problema.
     */
    public Client[] getClients() {
        return clients;
    }

    /**
     * Seteaza clientii pentru problema.
     *
     * @param clients Un array de obiecte Client care reprezinta clientii ce urmeaza a fi setati.
     */
    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }

    /**
     * Sorteaza array-ul de clienti in functie de timpul de inceput al intervalului disponibilitatii clientilor.
     */
    public void sortClientsByStartTime() {
        int n = clients.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (clients[i].getStartTime().isAfter(clients[j].getStartTime())) {
                    Client temp = clients[j];
                    clients[j] = clients[i];
                    clients[i] = temp;
                }
            }
        }
    }

    /**
     * Gaseste indexul turului cu timpul minim de calatorie pentru un client.
     *
     * @param t Un array de numere intregi reprezentand timpul in minute de calatorie generat aleatoriu.
     * @param indexClient Indexul clientului pentru care se calculeaza timpul minim.
     * @param currentTime Timpul curent folosit pentru calcularea timpului de calatorie.
     * @return Indexul turului cu timpul minim de calatorie pentru client.
     */
    public int minTime(int[] t, int indexClient, LocalTime currentTime) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < t.length; i++) {
            if (t[i] < min && currentTime.plusMinutes(t[i]).isAfter(clients[indexClient].getStartTime()) && currentTime.plusMinutes(t[i]).isBefore(clients[indexClient].getEndTime())) {
                min = t[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
