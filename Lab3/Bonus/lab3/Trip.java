package Bonus.lab3;

import java.time.LocalDate;
import java.util.*;

public class Trip {
    private String city;
    private LocalDate start, end;
    private List<Node> attractions = new ArrayList<>();
    private List<TravelPlan> travel;

    public Trip() {
    }

    public Trip(String city, LocalDate start, LocalDate end) {
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public Trip(String city, LocalDate start, LocalDate end, List<Node> attractions) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.attractions = attractions;
    }

    public void displayVisitableNotPayableLocations() {
        List<Visitable> visitableLocation = new ArrayList<>();
        for (Node atr : attractions) {
            if (atr instanceof Visitable && !(atr instanceof Payable)) {
                visitableLocation.add((Visitable) atr);
            }
        }
        visitableLocation.sort(Comparator.comparing(a -> a.getOpeningHour(start)));
        for (Visitable v : visitableLocation) {
            System.out.println(v);
        }
    }

    public void addAttraction(Node atr) {
        attractions.add(atr);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void heuristicColoring() {
        List<TravelPlan> travel = new ArrayList<>();
        Graph graph = new Graph();
        for (Node attraction : attractions) {
            graph.addNode(attraction);
        }
        graph.createGraph();
        graph.getGraph().sort((a, b) -> Integer.compare(b.getNumOfNeighbors(), a.getNumOfNeighbors()));
        List<Node> travelPlan = graph.getGraph();
        for (Node node : travelPlan) {
            Visitable attraction = (Visitable) node.getAttraction();
            List<LocalDate> timetable = new ArrayList<>(attraction.getTimetable().keySet());
            Collections.sort(timetable);
            for (LocalDate v : timetable) {
                if (v.isAfter(start) && v.isBefore(end)) {
                    boolean isAllocatable = true;
                    for (TravelPlan entry : travel) {
                        if (entry.getPlan().getFirst().isEqual(v) && entry.getPlan().getSecond().isNeighbors(node)) {
                            isAllocatable = false;
                        }
                    }
                    if (isAllocatable) {
                        node.addColor(v);
                        travel.add(new TravelPlan(new TravelPair(v, node)));
                        break;
                    }
                }
            }
        }
        this.travel = travel;
    }

    public void print() {
        travel.sort(Comparator.comparing(a -> a.getPlan().getFirst()));
        LocalDate lastDate = LocalDate.of(2000, 1, 1);
        for (TravelPlan day : travel) {
            if (!day.getPlan().getFirst().isEqual(lastDate)) {
                System.out.println();
                System.out.print(day.getPlan().getFirst() + " : " + day.getPlan().getSecond());
                lastDate = day.getPlan().getFirst();
            } else System.out.print(", " + day.getPlan().getSecond());
        }
    }


    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractions=" + attractions +
                '}';
    }
}
