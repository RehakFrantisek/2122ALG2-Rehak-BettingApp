package app;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;

import static utils.FileUtils.readCSV;

public class Ticket {
    private ArrayList<Bet> bets = new ArrayList<>();

    public Ticket() {
    }

    public void addBet(Bet bet){
        this.bets.add(bet);
    }
}
