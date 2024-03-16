package Bonus.lab3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<LocalDate, IntervalTime> getTimetable();
    default LocalTime getOpeningHour(LocalDate date){
        Map<LocalDate, IntervalTime> timetable = getTimetable();
       return timetable.containsKey(date)? timetable.get(date).getFirst() : null;
    }
}
