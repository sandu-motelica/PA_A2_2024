package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityGraph {
    private Map<Location, Set<Location>> adjacencyList;
    private Map<Integer, Location> locationsById;

    public CityGraph() {
        this.adjacencyList = new HashMap<>();
        this.locationsById = new HashMap<>();
    }

    public void addLocation(Location location) {
        if (!adjacencyList.containsKey(location)) {
            adjacencyList.put(location, new HashSet<>());
            locationsById.put(location.getId(), location);
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

    public Location getLocationById(int id) {
        return locationsById.get(id);
    }
}
