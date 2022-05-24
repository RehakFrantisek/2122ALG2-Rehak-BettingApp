package app;

import utils.FileUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    protected String username;
    protected int PID;
    protected String password;
    protected String cardnumber;
    protected int cvc;
    protected int wallet;

    protected ArrayList<Ticket> tickets = new ArrayList<>();

    public User(String username, String password, int PID, String cardnumber, int cvc, int wallet) {
        this.username = username;
        this.PID = PID;
        this.password = password;
        this.cardnumber = cardnumber;
        this.cvc = cvc;
        this.wallet = wallet;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getWallet() {return wallet; }

    public String getCardnumber() { return cardnumber; }

    public void addMoney(int amount){
        this.wallet += amount;
    }

    public void removeMoney(int amount){
        this.wallet -= amount;
    }

    public int getPID() {
        return PID;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void loadTickets(){
        String[] rows = FileUtils.readCSV("data//" + this.username + "//bets.csv");
        for(String row : rows){
            String[] parms = row.split(";");
            Bet newBet = new Bet(parms[0], parms[1], Float.parseFloat(parms[2]), Float.parseFloat(parms[3]), Float.parseFloat(parms[4]), LocalDate.parse(parms[5]), LocalTime.parse(parms[6]));
            Ticket newTicket = new Ticket(newBet, Integer.parseInt(parms[7]), Integer.parseInt(parms[8]), parms[9]);
            this.addTicket(newTicket);
        }
    }

    @Override
    public String toString() {
        return username+";"+PID+";"+password+";"+cardnumber+";"+cvc+";"+wallet;
    }
}
