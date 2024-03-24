package lab4;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private final List<Person> persons;

    public Problem(List<Person> persons) {
        this.persons = persons;
    }

    public List<String> destinationsOfDrivers() {
        return persons.stream()
                .map(Person::getDestination)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Map<String, List<Person>> destinationsAndPeople() {
        return persons.stream().collect(Collectors.groupingBy(Person::getDestination));
    }

    public void matchDriversAndPassengers() {
        Map<Person, Person> matches = new HashMap<>();

        Map<String, List<Person>> passengersByDestination = passengersByDestination();

        for (Person driver : getDrivers()) {
            String destination = driver.getDestination();
            List<Person> passengers = passengersByDestination.getOrDefault(destination, new ArrayList<>());

            if (!passengers.isEmpty()) {
                matches.put(driver, passengers.get(0));
                passengers.remove(passengers.get(0));
            }
        }

        System.out.println("\nMatched Drivers and Passengers:");
        for (Map.Entry<Person, Person> entry : matches.entrySet()) {
            System.out.println("Driver: " + entry.getKey() + " --> Passenger: " + entry.getValue());
        }
    }

    private List<Person> getDrivers() {
        return persons.stream()
                .filter(person -> person instanceof Driver)
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

    private Map<String, List<Person>> passengersByDestination() {
        return persons.stream()
                .filter(person -> person instanceof Passenger)
                .collect(Collectors.groupingBy(Person::getDestination));
    }

}


