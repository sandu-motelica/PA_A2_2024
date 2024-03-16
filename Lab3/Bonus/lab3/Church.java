package Bonus.lab3;

import java.time.LocalDate;
import java.util.Map;

public class Church extends Attraction implements Visitable {
    private Map<LocalDate, IntervalTime> timetable;

    public Church() {
    }

    public Church(String name, Map<LocalDate, IntervalTime> timetable) {
        super(name);
        this.timetable = timetable;
    }

    @Override
    public Map<LocalDate, IntervalTime> getTimetable() {
        return timetable;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setTimetable(Map<LocalDate, IntervalTime> timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getType() {
        return "Church";
    }

    @Override
    public String toString() {
        return "Church{ name=" + getName() +
                ", timetable=" + timetable +
                '}';
    }
}
