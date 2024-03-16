package Bonus.lab3;

import java.time.LocalTime;

public class IntervalTime extends Pair<LocalTime, LocalTime> {
    public IntervalTime(LocalTime first, LocalTime second) {
        super(first, second);
    }

    @Override
    public LocalTime getFirst() {
        return super.getFirst();
    }


    @Override
    public String toString() {
        return "[" + getFirst() + ", " + getSecond() + "]";
    }
}
