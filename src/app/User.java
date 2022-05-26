package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Frantisek Rehak
 */
public class User {
    /**
     * This is class representing User
     */
    private String username;
    private int PID;
    private String password;
    private String cardnumber;
    private int cvc;
    private float wallet;

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

    public void removeMoney(int amount){
        /**
         * This method remove money from user.
         * @param amount Taken money.
         */
        this.wallet -= amount;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Method loadTickets load a file bets.csv.
     */
    public void loadTickets(){
        /**
         * This method load tickets from homedir file bets.csv.
         * Adding them to ArrayList tickets.
         */
        File checkFile = new File("data//"+this.username+"//bets.csv");
        if(checkFile.length() != 0) {
            //this.tickets.clear();
            String[] rows = FileUtils.readCSV("data//" + this.username + "//bets.csv");
            for (String row : rows) {
                String[] parms = row.split(";");
                Bet newBet = new Bet(parms[0], parms[1], Float.parseFloat(parms[2]), Float.parseFloat(parms[3]), Float.parseFloat(parms[4]), LocalDate.parse(parms[5]), LocalTime.parse(parms[6]));
                Ticket newTicket = new Ticket(newBet, Integer.parseInt(parms[7]), Integer.parseInt(parms[8]), parms[9]);
                if (!checkDuplicity(newTicket)) {
                    this.tickets.add(newTicket);
                }
            }
        }
    }

    /* duplicity ticket check */
    private boolean checkDuplicity(Ticket ticket){
        /**
         * This method check if ticket is not already in ArrayList tickets.
         * @param ticket Ticket what is checked.
         * @return Return true or false depends if its in or not.
         */
        for(Ticket ticket1 : this.tickets){
            if(ticket1.equals(ticket)){
                return true;
            }
        }
        return false;
    }

    public void updateTickets(){
        /**
         * This method update tickets from file.
         */
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


    public void checkTickets(){
        /**
         * This method evaluate all tickets that are outdated, making them win/lose randomly.
         * All tickets where today date didnt pass ticket's date are ignored.
         */
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
            else if (ticket.getBet().getDate().compareTo(LocalDate.now()) == 0 && ticket.getBet().getTime().compareTo(LocalTime.now()) < 0 && ticket.getStatus().equals("Waiting")){
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
        /**
         * Method saveToPdf saves actual ticket history
         * to PDF file in their file (data//'username')
         *
         * @param username user's tickets that are saved
         * @return
         */
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data//"+username+"//statistics.pdf"));

            document.open();
            document.add(new Paragraph("Username: " + username));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(tickets.toString()));
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
