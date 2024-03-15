package Homework.lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Trip {
    private String city;
    private LocalDate start, end;
    private List<Attraction> attractions = new ArrayList<>();

    public Trip(){}
    public Trip(String city, LocalDate start, LocalDate end) {
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public Trip(String city, LocalDate start, LocalDate end, List<Attraction> attractions) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.attractions = attractions;
    }

    public void displayVisitableNotPayableLocations(){
        List<Visitable> visitableLocation = new ArrayList<>();
        for(Attraction atr : attractions){
            if(atr instanceof Visitable && !(atr instanceof Payable)){
                visitableLocation.add((Visitable)atr);
            }
        }
        visitableLocation.sort(Comparator.comparing(a->a.getOpeningHour(start)));
        for(Visitable v: visitableLocation){
            System.out.println(v);
        }
    }

    public void addAttraction(Attraction atr){
        attractions.add(atr);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
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
