package lab4;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        LinkedList<Person> persons = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> {
                    String name = "Person" + i;
                    int age = i + 18;
                    String destination = "Destination" + random.nextInt(10);
                    return random.nextBoolean() ? new Driver(name, age, destination) : new Passenger(name, age, destination);
                })
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Driver> drivers = persons.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toCollection(LinkedList::new));

        TreeSet<Passenger> passengers = persons.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Drivers: ");
        drivers.stream()
                .sorted(Comparator.comparingInt(Driver::getAge))
                .forEach(System.out::println);

        System.out.println("Passengers:");
        passengers.stream()
                .sorted(Comparator.comparing(Passenger::getName))
                .forEach(System.out::println);
    }
}
