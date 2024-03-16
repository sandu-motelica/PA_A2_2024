package Bonus.lab3;

import java.time.LocalDate;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable {
    private Map<LocalDate, IntervalTime> timetable;
    private double ticketPrice;

    public Map<LocalDate, IntervalTime> getTimetable() {
        return timetable;
    }

    public Concert() {
    }

    public Concert(String name, Map<LocalDate, IntervalTime> timetable, double ticketPrice) {
        super(name);
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public void setTimetable(Map<LocalDate, IntervalTime> timetable) {
        this.timetable = timetable;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String getType() {
        return "Concert";
    }

    @Override
    public String toString() {
        return "Concert{ name=" + getName() +
                ", timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
