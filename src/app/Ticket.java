package app;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;

import static utils.FileUtils.readCSV;

public class Ticket {
    private Bet bet = null;
    private int who;

    private int amount;

    private String status = "Waiting";

    public Ticket(Bet bet, int who, int amount) {
        this.bet = bet;
        this.who = who;
        this.amount = amount;
    }

    public Ticket(Bet bet, int who, int amount, String status) {
        this.bet = bet;
        this.who = who;
        this.amount = amount;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Override
    public String toString() {
        return bet.toString() + ";" + who + ";" + amount + ";" + status;
    }
}
