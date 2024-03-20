package lab4;


public abstract class Person {
    private String name;
    int age;
    private String destination;

    Person() {}
    Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDestination() {
        return destination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", age -> " + age + ", destination -> " + destination;
    }

    public abstract int compareTo(Passenger otherDriver);
}

