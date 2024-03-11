package Homework.lab2;

import java.util.Arrays;

/**
 * Clasa Tour reprezinta un tur(o solutie a problemei) care contine un vehicul si o lista de clienti.
 *
 */
public class Tour {
    private Vehicle vehicle;
    private Client[] clients;


    /**
     * Construieate un tur cu vehiculul ai lista de clienti ai iniaializate ca nule.
     */
    public Tour() {
        clients = null;
        vehicle = null;
    }

    /**
     * Obtine vehicolul asociat cursei.
     *
     * @return Vehicolul asociat cursei.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }
    /**
     * Seteaza vehicolul asociat cursei.
     * @param vehicle Noul vehicul asociat cursei.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Obtine toti clienti serviti in actuala cursa.
     *
     * @return Un array de obiecte Client care contine toti clienti serviti.
     */
    public Client[] getClients() {
        return clients;
    }
    /**
     * Adauga un client la lista de clienti ai ai turului.
     *
     * @param client Clientul de adaugat la tur.
     */
    public void addClient(Client client) {
        Client[] tempClients;
        if(clients == null) {
            tempClients = new Client[1];
            tempClients[0] = client;
        }
        else {
            tempClients = new Client[clients.length + 1];

            int index = 0;
            for (Client c : clients) {
                tempClients[index++] = c;
            }
            tempClients[index] = client;
        }
        clients = tempClients;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "vehicle=" + vehicle +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
