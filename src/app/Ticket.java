package app;

import java.util.ArrayList;

public class Ticket {
    private ArrayList<Bet> bets = new ArrayList<Bet>();

    public Ticket() {
    }

    public void addBet(Bet bet){
        this.bets.add(bet);
    }
}
