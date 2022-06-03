package utils;

import app.Ticket;

import java.util.Comparator;

/**
 *
 * @author Frantisek Rehak
 */
public class ComparatorByStatus implements Comparator<Ticket>{

    private int getPriority(String status){
        return switch (status){
            case "Win" -> 1;
            case "Draw" -> 2;
            case "Lost" -> 3;
            default -> 0;
        };
    }

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return
                Integer.compare(getPriority(o1.getStatus()), getPriority(o2.getStatus()));
    }
}
