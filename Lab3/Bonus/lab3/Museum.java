package Bonus.lab3;

import java.time.LocalDate;
import java.util.Map;

public class Museum extends Attraction implements Visitable, Payable {
    private Map<LocalDate, IntervalTime> timetable;
    private double ticketPrice;

    public Museum() {
    }

    public Museum(String name, Map<LocalDate, IntervalTime> timetable, double ticketPrice) {
        super(name);
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public Map<LocalDate, IntervalTime> getTimetable() {
        return timetable;
    }

    public void setTimetable(Map<LocalDate, IntervalTime> timetable) {
        this.timetable = timetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String getType() {
        return "Museum";
    }

    @Override
    public String toString() {
        return "Museum{ name=" + getName() +
                ", timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
