package Bonus.lab3;

public abstract class Attraction implements Comparable<Attraction> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attraction() {
    }

    public Attraction(String name) {
        this.name = name;
    }

    public String getType() {
        return "Attraction";
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Attraction other) {
        return this.name.compareTo(other.name);
    }

}
