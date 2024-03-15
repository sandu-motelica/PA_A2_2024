package Homework.lab3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TravelPlan {
    private Map<LocalDate, Attraction> plan;
    public TravelPlan(){
        plan = new HashMap<>();
    }

    public TravelPlan(Map<LocalDate, Attraction> plan) {
        this.plan = plan;
    }

    public void addPlan(LocalDate date, Attraction attraction){
        plan.put(date, attraction);
    }

    public Map<LocalDate, Attraction> getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "plan=" + plan +
                '}';
    }
}
