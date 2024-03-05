import lab2.*;
/**
 * @author MotelicaSandu
 */

public class Lab2 {
    public static void main(String[] args) {
        Client client = new Client("Ion", ClientType.PREMIUM, 3, 10);
        Depot depot = new Depot("Copou");
        Vehicle vehicle = new Vehicle("ISX153", 7, depot);

        System.out.println("Client: " + client);
        System.out.println("Depot: " + depot);
        System.out.println("Vehicle: " + vehicle);
    }
}
