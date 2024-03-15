package Homework.lab3;

public class Statue extends Attraction{
    private String description;
    public Statue(){}
    public Statue(String name, String description){
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Statue{" +
                "description='" + description + '\'' +
                '}';
    }
}
