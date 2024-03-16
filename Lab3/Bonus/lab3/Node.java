package Bonus.lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private Attraction attraction;
    private List<LocalDate> colors = new ArrayList<>();
    private List<Node> neighbors = new ArrayList<>();
    private int numOfNeighbors;


    public Node(Attraction attraction) {
        this.attraction = attraction;
        numOfNeighbors = 0;
    }

    public void addNeighbors(Node node) {
        this.neighbors.add(node);
        this.numOfNeighbors++;
    }

    public boolean isNeighbors(Node node) {
        return neighbors.contains(node);
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public int getNumOfNeighbors() {
        return numOfNeighbors;
    }

    public void addColor(LocalDate color) {
        colors.add(color);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("Node { ");
        message.append(attraction.getType());
        message.append(" = ");
        message.append(attraction.getName());
        if (!neighbors.isEmpty()) {
            message.append(", neighbors = ");
            int index = 0;
            for (Node node : neighbors) {
                message.append(node.getAttraction().getName());
                index++;
                if (index != neighbors.size())
                    message.append(", ");
            }
        }
        message.append(" }");
        return message.toString();
    }
}
