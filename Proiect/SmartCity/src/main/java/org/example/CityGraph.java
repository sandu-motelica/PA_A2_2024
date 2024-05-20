package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityGraph {
    private Map<Location, Set<Location>> adjacencyList;

    public CityGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addLocation(Location location) {
        if (!adjacencyList.containsKey(location)) {
            adjacencyList.put(location, new HashSet<>());
        }
    }

    public void addConnection(Location from, Location to) {
        if (!adjacencyList.containsKey(from)) {
            addLocation(from);
        }
        if (!adjacencyList.containsKey(to)) {
            addLocation(to);
        }
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }

    public Set<Location> getLocations() {
        return adjacencyList.keySet();
    }

    public Set<Location> getConnectedLocations(Location location) {
        return adjacencyList.get(location);
    }
}
