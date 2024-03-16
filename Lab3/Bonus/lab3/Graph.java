package Bonus.lab3;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> graph;

    public Graph() {
        this.graph = new ArrayList<>();
    }

    public void addNode(Node node) {
        graph.add(node);
    }

    public void createGraph() {
        for (int i = 0; i < graph.size() - 1; i++) {
            for (int j = i + 1; j < graph.size(); j++) {
                if (sameType(graph.get(i).getAttraction(), graph.get(j).getAttraction())) {
                    graph.get(i).addNeighbors(graph.get(j));
                    graph.get(j).addNeighbors(graph.get(i));
                }
            }
        }
    }

    public List<Node> getGraph() {
        return graph;
    }
    public void setGraph(List<Node> g) {
        this.graph = g;
    }
    public static boolean sameType(Attraction attraction1, Attraction attraction2) {
        return attraction1.getClass() == attraction2.getClass();
    }

}
