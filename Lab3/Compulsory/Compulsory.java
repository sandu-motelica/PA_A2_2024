import lab3.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Compulsory {
    Attraction statue = new Statue("Statue of Liberty");
    Attraction church = new Church("Sf. Pavel");
    Attraction concert = new Concert("Concert", 50.0);

    List<Attraction> attractions = new ArrayList<>();
    attractions.add(statue);
    attractions.add(church);
    attractions.add(concert);

    Trip trip = new Trip("Paris", LocalDate.now(), LocalDate.now(), attractions);

}
