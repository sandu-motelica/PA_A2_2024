package lab4;


public class Driver extends Person implements Comparable<Driver> {
    Driver() {}
    public Driver(String name, int age, String destination) {
        super(name, age, destination);
    }

    @Override
    public String toString() {
        return "Driver: " + getName() + ", age: " + getAge() + ", destination: " + getDestination();
    }

    @Override
    public int compareTo(Passenger otherDriver) {
        return 0;
    }

    @Override
    public int compareTo(Driver otherDriver) {
        return this.getName().compareTo(otherDriver.getName());
    }

}
