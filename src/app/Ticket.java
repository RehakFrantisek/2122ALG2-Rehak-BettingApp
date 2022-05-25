package app;

/**
 *
 * @author Frantisek Rehak
 */
public class Ticket {
    /**
     * This is class representing Ticket
     */
    private Bet bet;
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

    public int getWho() {
        return who;
    }

    public int getAmount() {
        return amount;
    }

    public void completeTicket(boolean won){
        /**
         * This method change status of ticket.
         * @param won True or false if ticket is win one.
         */
        if(won) this.status = "Won";
        else this.status = "Lost";
    }

    public String getStatus() {
        return status;
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public String toString() {
        return bet.toString() + ";" + who + ";" + amount + ";" + status;
    }
}
