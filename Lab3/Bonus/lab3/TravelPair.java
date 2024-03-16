package Bonus.lab3;

import java.time.LocalDate;

public class TravelPair extends Pair<LocalDate, Node> {
    public TravelPair(LocalDate first, Node second) {
        super(first, second);
    }

    @Override
    public LocalDate getFirst() {
        return super.getFirst();
    }

    @Override
    public Node getSecond() {
        return super.getSecond();

    }
}