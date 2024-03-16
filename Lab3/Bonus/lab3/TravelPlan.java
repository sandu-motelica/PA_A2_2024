package Bonus.lab3;

public class TravelPlan {
    private TravelPair plan;


    public TravelPlan(TravelPair plan) {
        this.plan = plan;
    }

    public void setPlan(TravelPair plan) {
        this.plan = plan;
    }

    public TravelPair getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "plan=" + plan +
                '}';
    }
}
