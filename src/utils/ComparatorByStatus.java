package utils;

import app.Ticket;
import java.util.Comparator;

public class ComparatorByStatus implements Comparator<Ticket>{

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getStatus().compareTo(o2.getStatus());
    }
}
