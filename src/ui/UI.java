package ui;

import app.*;
import utils.ComparatorByStatus;
import utils.FileUtils;
import java.time.LocalTime;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.*;

/**
 *
 * @author Frantisek Rehak
 */
public class UI {

    private User loggedUser = null;
    private IBetCompany betCompany;
    private final Scanner sc = new Scanner(System.in);
    private static String formatted;

    public UI(IBetCompany betCompany){
        this.betCompany = betCompany;
    }

    /* generates main page with name of App */
    public void mainPage(){
        System.out.println();
        System.out.println(this.betCompany.getName());
        System.out.println("- APP -");
    }

    /* intro page with methods to load data */
    public void intro() throws IOException, ParseException {
        FileUtils.createFolder("data");
        FileUtils.createFile("login.csv","data");
        betCompany.loadUsers();
        betCompany.loadMoney();
        betCompany.loadBets();
        //System.out.println(betCompany.toStringBets());
        while(true){
            System.out.println();
            loginMenu();
            int option;
            while(!sc.hasNextInt()){
                System.out.println();
                System.out.println("Not a number");
                System.out.println("Choose 0-2");
                System.out.println("");
                loginMenu();
                sc.next();
            }
            option = sc.nextInt();
            switch(option){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    quit();
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    /* callable login menu */
    private void loginMenu(){
        StringBuffer buffer = new StringBuffer();
        Formatter formatter = new Formatter(buffer, Locale.US);
        formatter.format("1) login \n2) register \n0) quit");
        System.out.println(formatter);
    }

    /* login with 'username//bets.csv' check */
    private void login() throws IOException {
        System.out.println();
        System.out.println("Username");
        String username = sc.next();
        System.out.println("Password");
        String password = sc.next();
        while(!betCompany.checkLogin(username, password)){
            System.out.println("Username or password doesnt exists");
            return;
        }
        System.out.println(username+" logged");
        this.loggedUser = betCompany.getUserByUsername(username);
        /*
        if(FileUtils.doesFileExists("data//" + this.loggedUser.getUsername() + "//bets.csv")){
            this.loggedUser.loadTickets();
            this.loggedUser.checkTickets();
            this.loggedUser.updateTickets();
            this.betCompany.updateUsers();
        }else{
            FileUtils.createFile(this.loggedUser.getUsername()+"//bets.csv","data");
        }*/
        System.out.println(LocalTime.now());
        letsBet();
    }

    private void letsBetMenu(){
        System.out.println("----------");
        System.out.println("1) new ticket");
        System.out.println("2) active tickets");
        System.out.println("3) bet history");
        System.out.println("4) bet history sorted by bet value (ascending)");
        System.out.println("5) bet history sorted by bet value (descending)");
        System.out.println("6) logout");
    }

    /* betting menu */
    private void letsBet(){
        while(true) {
            //this.loggedUser.loadTickets();
            //this.loggedUser.checkTickets();
            //this.loggedUser.updateTickets();
            System.out.println();
            System.out.println("Lets BET");
            System.out.println("----------");
            System.out.println(loggedUser.getUsername());
            formatted = String.format("$ %.1f",loggedUser.getWallet());
            System.out.println(formatted);
            letsBetMenu();
            int letsBetOption;
            while(!sc.hasNextInt()){
                System.out.println();
                System.out.println("Not a number");
                System.out.println("Choose 1-6");
                letsBetMenu();
                sc.next();
            }
            letsBetOption = sc.nextInt();
            switch (letsBetOption) {
                case 1:
                    newTicket();
                    break;
                case 2:
                    myTickets();
                    break;
                case 3:
                    myHistory();
                    break;
                case 4:
                    myHistoryByMoney();
                    break;
                case 5:
                    myHistoryByLMoney();
                    break;
                case 6:
                    logout();
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    /* place new ticket menu with methods */
    private void newTicket() {
        System.out.println();
        System.out.println("New Ticket");
        System.out.println("----------");
        System.out.println(loggedUser.getUsername());
        System.out.println();
        System.out.print("Disponsible money: ");
        System.out.println("$ "+loggedUser.getWallet());
        if(loggedUser.getWallet() == 0){
            System.out.println("Not enough money");
            return;
        }
        System.out.println("----------");
        System.out.println(betCompany.toStringBets());
        System.out.println("Number of match");
        int matchRow;
        do{
            System.out.println("Choose match: ");
            while(!sc.hasNextInt()){
                System.out.println();
                System.out.println("Not a number");
                System.out.println("Choose match: ");
                sc.next();
            }
            matchRow = sc.nextInt();
        } while (matchRow <= 0 || matchRow >= 9);
        Bet myBet = this.betCompany.getBetByIndex(matchRow);
        int winnerOption;
        do{
            System.out.println("Your bet ( 1 - homeWin, 2 - draw, 3 - awayWin)");
            System.out.println("What are you betting at: ");
            while(!sc.hasNextInt()){
                System.out.println();
                System.out.println("Not a number");
                System.out.println("Choose 1-3 (homeWin, draw, awayWin)");
                System.out.println("What are you betting at: ");
                sc.next();
            }
            winnerOption = sc.nextInt();
        } while (winnerOption <=0 || winnerOption >=4);
        System.out.println("How much money you want bet: ");
        while(!sc.hasNextInt()){
            System.out.println();
            System.out.println("Not a number");
            System.out.println("Choose amount of money");
            sc.next();
        }
        int moneyOption = sc.nextInt();
        if(loggedUser.getWallet() < moneyOption){
            System.out.println("You tried to bet more then you could");
            return;
        }
        Ticket myTicket = new Ticket(myBet, winnerOption, moneyOption);
        this.loggedUser.addTicket(myTicket);
        this.loggedUser.removeMoney(moneyOption);
        betCompany.updateUsers();
        FileUtils.appendToFile("data//" + this.loggedUser.getUsername() + "//bets.csv", myTicket.toString());
        this.loggedUser.checkTickets();
        this.loggedUser.updateTickets();
    }

    /* bet history (tickets with status "waiting") */
    private void myTickets(){
        StringBuilder sb = new StringBuilder();
        if(this.loggedUser.getTickets().isEmpty()){
            System.out.println("No active tickets");
        }else{
            for(Ticket ticket : this.loggedUser.getTickets()){
                if(ticket.getStatus().equals("Waiting")) {
                    sb.append(ticket.toString()).append("\n");
                }
            }
            System.out.println("");
            System.out.println("My active tickets");
            System.out.println(sb.toString());
        }
    }

    /* bet history (tickets with status "!waiting") */
    private void myHistory() {
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser.getTickets().isEmpty()) {
            System.out.println("No bet history");
        } else {
            for (Ticket ticket : this.loggedUser.getTickets()) {
                if (!ticket.getStatus().equals("Waiting")) {
                    sb.append(ticket.toString()).append("\n");
                }
            }
            System.out.println("");
            System.out.println("My bet history");
            System.out.println(sb.toString());
        }
    }

    /* bet history by betted money */
    private void myHistoryByMoney() {
        ArrayList<Ticket> ticketByWin = new ArrayList<>();
        for (Ticket ticket : this.loggedUser.getTickets()){
            ticketByWin.add(ticket);
        }
        Collections.sort(ticketByWin, new ComparatorByStatus().reversed());
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser.getTickets().isEmpty()) {
            System.out.println("No bet history");
        } else {
            for (Ticket ticketByWins : ticketByWin){
                if (!ticketByWins.getStatus().equals("Waiting")) {
                    sb.append(ticketByWins.toString()).append("\n");
                }
            }
            System.out.println("");
            System.out.println("My bet history by money (ascending)");
            System.out.println(sb.toString());
        }
    }

    /* bet history by betted money reversed */
    private void myHistoryByLMoney() {
        ArrayList<Ticket> ticketByWin = new ArrayList<>();
        for (Ticket ticket : this.loggedUser.getTickets()){
            ticketByWin.add(ticket);
        }
        Collections.sort(ticketByWin, Comparator.comparing(Ticket::getAmount));
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser.getTickets().isEmpty()) {
            System.out.println("No bet history");
        } else {
            for (Ticket ticketByWins : ticketByWin){
                if (!ticketByWins.getStatus().equals("Waiting")) {
                    sb.append(ticketByWins.toString()).append("\n");
                }
            }
            System.out.println();
            System.out.println("My bet history by money (descending)");
            System.out.println(sb.toString());
        }
    }

    /* register menu */
    private void register(){
        System.out.println("Username");
        String username = sc.next();
        if(!betCompany.checkUsername(username)){
            while(!betCompany.checkUsername(username)){
                System.out.println("Username taken");
                return;
            }
        }
        System.out.println("Password");
        String password = sc.next();
        System.out.println("Personal_ID");
        while(!sc.hasNextInt()){
            System.out.println();
            System.out.println("Not a number");
            System.out.println("Personal_ID");
            sc.next();
        }
        int PID = sc.nextInt();
        System.out.println("Card number");
        String cardnumber = sc.next();
        if(betCompany.checkCardnumber(cardnumber)){
            while(betCompany.checkCardnumber(cardnumber)){
                System.out.println("Cardnumber taken, choose another");
                cardnumber = sc.next();
            }
        }
        System.out.println("CVC code (3 digits)");
        while(!sc.hasNextInt()){
            System.out.println();
            System.out.println("Not a 3-digit number");
            System.out.println("CVC code");
            sc.next();
        }
        int cvc = sc.nextInt();
        Pattern pattern = Pattern.compile("^\\d\\d\\d$");
        Matcher matcher = pattern.matcher(Integer.toString(cvc));
        boolean matchFound = matcher.find();
        if(!matchFound) {
            System.out.println("Wrong input (number doesnt correspond to the 3 digit number)");
            System.out.println("Try again");
        }
        else{
            float wallet = betCompany.getMoneyByCardnumber(cardnumber, username);
            User reguser = new User(username,password,PID,cardnumber,cvc, wallet);
            FileUtils.createFolder("data//"+username);
            this.betCompany.addUser(reguser);
            betCompany.bankMoney();
        }
    }

    /* logout menu */
    private void logout(){
        //this.loggedUser.loadTickets();
        //this.loggedUser.checkTickets();
        //this.loggedUser.updateTickets();
        System.out.println();
        System.out.println(loggedUser.getUsername()+ " you are gonna be logged out");
        System.out.println();
        loggedUser.saveToPdf(this.loggedUser.getUsername());
        this.loggedUser = null;
    }

    /* quit menu */
    private void quit() throws IOException, ParseException {
        while(true){
            System.out.println("Are you sure to quit?");
            System.out.println("0) for quit");
            System.out.println("1) to return to the main page");
            while(!sc.hasNextInt()){
                System.out.println();
                System.out.println("Wrong input");
                System.out.println("0) for quit");
                System.out.println("1) to return to the main page");
                sc.next();
            }
            try{
                switch(QuitType.values()[sc.nextInt()]){
                    case YES:
                        return;
                    case NO:
                        intro();
                        return;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }catch(Exception e){
                System.out.println();
                System.out.println("Wrong input");
            }
        }
    }
}
