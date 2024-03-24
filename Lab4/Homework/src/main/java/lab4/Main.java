package lab4;

import com.github.javafaker.Faker;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Faker faker = new Faker();
        String[] cites = new String[4];
        for (int i = 0; i < cites.length; i++) {
            cites[i] = faker.address().city();
        }
        LinkedList<Person> persons = IntStream.rangeClosed(1, 9)
                .mapToObj(i -> {
                    String name = faker.name().fullName();
                    int age = 18 + random.nextInt(40);
                    String destination = cites[random.nextInt(4)];
                    return random.nextBoolean() ? new Driver(name, age, destination) : new Passenger(name, age, destination);
                })
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Driver> drivers = persons.stream()
                .filter(person -> person instanceof Driver)
                .sorted(Comparator.comparing(Person::getAge))
                .map(person -> (Driver) person)
                .collect(Collectors.toCollection(LinkedList::new));

        TreeSet<Passenger> passengers = persons.stream()
                .filter(person -> person instanceof Passenger)
                .sorted(Comparator.comparing(Person::getName))
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Drivers: ");
        drivers.forEach(System.out::println);

        System.out.println("Passengers:");
        passengers.forEach(System.out::println);

        Problem p = new Problem(persons);
        p.matchDriversAndPassengers();
    }
}
