package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {
    protected String username;
    protected int PID;
    protected String password;
    protected String cardnumber;
    protected int cvc;
    protected float wallet;

    protected ArrayList<Ticket> tickets = new ArrayList<>();

    public User(String username, String password, int PID, String cardnumber, int cvc, float wallet) {
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

    public float getWallet() {return wallet; }

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
        this.tickets.clear();
        String[] rows = FileUtils.readCSV("data//" + this.username + "//bets.csv");
        for(String row : rows){
            String[] parms = row.split(";");
            Bet newBet = new Bet(parms[0], parms[1], Float.parseFloat(parms[2]), Float.parseFloat(parms[3]), Float.parseFloat(parms[4]), LocalDate.parse(parms[5]), LocalTime.parse(parms[6]));
            Ticket newTicket = new Ticket(newBet, Integer.parseInt(parms[7]), Integer.parseInt(parms[8]), parms[9]);
            if(!checkDuplicity(newTicket)){
                this.tickets.add(newTicket);
            }
        }
    }

    public boolean checkDuplicity(Ticket ticket){
        for(Ticket ticket1 : this.tickets){
            if(ticket1.equals(ticket)){
                return true;
            }
        }
        return false;
    }

    public void updateTickets(){
        String t = "";
        int val = 0;
        int max = this.tickets.size();
        for(Ticket ticket : this.tickets){
            if(max-1>val){
                t += ticket.toString() + "\n";
            }else{
                t += ticket.toString();
            }
            val++;
        }
        FileUtils.rWFile("data//" + this.username + "//bets.csv", t);
    }

    public Ticket getTicketByIndex(int rowIndex){
        return this.tickets.get(rowIndex-1);
    }

    public void checkTickets(){
        for(Ticket ticket : this.tickets){
            if(ticket.getBet().getDate().compareTo(LocalDate.now()) < 0 && ticket.getStatus().equals("Waiting")){
                int random = (int)(Math.random() * 49 + 1);
                if(random % 2 == 0){
                    ticket.completeTicket(true);
                    float odd = 0;
                    if(ticket.getWho() == 1){
                        odd = ticket.getBet().getHomeWin();
                    } else if (ticket.getWho() == 2) {
                        odd = ticket.getBet().getDraw();
                    }
                    else{
                        odd = ticket.getBet().getAwayWin();
                    }
                    this.wallet += (odd * ticket.getAmount());
                }
                else{
                    ticket.completeTicket(false);
                }
            }
        }
    }

    public boolean saveToPdf(String username) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data//"+username+"//statistics.pdf"));

            document.open();
            document.add(new Paragraph("Username: " + username));
            document.add(new Paragraph(" "));
            document.close();
            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (DocumentException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return username+";"+PID+";"+password+";"+cardnumber+";"+cvc+";"+wallet;
    }
}
