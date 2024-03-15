package Homework;

import Homework.lab3.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Homework {
    public static void main(String[] args) {
        Museum museum = new Museum();
        museum.setName("National");
        museum.setTicketPrice(12.5);
        Map<LocalDate, IntervalTime> timetable = new HashMap<>();
        timetable.put(LocalDate.now(), new IntervalTime(LocalTime.parse("08:00"), LocalTime.parse("18:00")));
        museum.setTimetable(timetable);
        Church church = new Church();
        church.setName("Sf. Pavel");
        Map<LocalDate, IntervalTime> timetable2 = new HashMap<>();
        timetable2.put(LocalDate.now(), new IntervalTime(LocalTime.parse("07:00"), LocalTime.parse("13:00")));
        church.setTimetable(timetable2);
        Trip trip = new Trip("Milan", LocalDate.now(), LocalDate.of(2024,5,12));
        trip.addAttraction(museum);
        trip.addAttraction(church);
        trip.displayVisitableNotPayableLocations();

        Map<LocalDate, Attraction> plan = new HashMap<>();
        plan.put(LocalDate.now(),museum);
        plan.put(LocalDate.of(2024,03,20),church);
        TravelPlan travelPlan = new TravelPlan(plan);
        System.out.println(travelPlan);

    }
}
