package Bonus;

import Bonus.lab3.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Bonus {
    public static void main(String[] args) {
        Trip trip = new Trip();
        trip.setCity("London");
        trip.setStart(LocalDate.of(2024, 3, 15));
        trip.setEnd(LocalDate.of(2024, 3, 22));
        Museum museum = new Museum();
        museum.setName("National");
        museum.setTicketPrice(12.5);
        Map<LocalDate, IntervalTime> timetable = new HashMap<>();
        timetable.put(LocalDate.now(), new IntervalTime(LocalTime.parse("08:00"), LocalTime.parse("18:00")));
        timetable.put(LocalDate.of(2024, 3, 17), new IntervalTime(LocalTime.parse("08:00"), LocalTime.parse("18:00")));
        museum.setTimetable(timetable);
        Museum museum1 = new Museum();
        museum1.setTicketPrice(20);
        museum1.setName("Regina Maria");
        museum1.setTimetable(timetable);
        Church church = new Church();
        church.setName("Sf. Pavel");
        Map<LocalDate, IntervalTime> timetable2 = new HashMap<>();
        timetable2.put(LocalDate.now(), new IntervalTime(LocalTime.parse("07:00"), LocalTime.parse("13:00")));
        timetable2.put(LocalDate.of(2024, 3, 18), new IntervalTime(LocalTime.parse("07:00"), LocalTime.parse("13:00")));
        timetable2.put(LocalDate.of(2024, 3, 19), new IntervalTime(LocalTime.parse("07:00"), LocalTime.parse("13:00")));
        church.setTimetable(timetable2);
        Concert concert = new Concert();
        concert.setName("Rhythm");
        Map<LocalDate, IntervalTime> timetable3 = new HashMap<>();
        timetable3.put(LocalDate.of(2024, 3, 18), new IntervalTime(LocalTime.parse("19:00"), LocalTime.parse("23:00")));
        timetable3.put(LocalDate.of(2024, 3, 20), new IntervalTime(LocalTime.parse("19:00"), LocalTime.parse("23:00")));
        concert.setTimetable(timetable3);
        Concert concert1 = new Concert();
        concert1.setName("Concert");
        concert1.setTimetable(timetable3);
        Concert concert2 = new Concert();
        concert2.setName("Harmony");
        concert2.setTimetable(timetable3);

        trip.addAttraction(new Node(museum1));
        trip.addAttraction(new Node(museum));
        trip.addAttraction(new Node(church));
        trip.addAttraction(new Node(concert));
        trip.addAttraction(new Node(concert1));
        trip.addAttraction(new Node(concert2));

        trip.heuristicColoring();
        trip.print();
    }
}
