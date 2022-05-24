package ui;


import app.User;
import utils.FileUtils;
import app.BetCompany;
import app.Money;
import app.Bet;
import app.Ticket;
import utils.OtherUtils;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Scanner;


public class UI {

    private User loggedUser = null;
    private BetCompany betCompany;
    private Money walletMoney;
    private Scanner sc = new Scanner(System.in);

    public UI(BetCompany betCompany){
        this.betCompany = betCompany;
    }

    public void mainPage(){
        System.out.println();
        System.out.println(this.betCompany.getName());
        System.out.println("- APP -");
    }

    public void intro() throws IOException, ParseException {
        FileUtils.createData();
        FileUtils.createFile("login.csv","data");
        betCompany.loadUsers();
        betCompany.loadMoney();
        betCompany.loadBets();
        //System.out.println(betCompany.toString());
        System.out.println(betCompany.toStringBets());
        while(true){
            System.out.println();
            System.out.println("1) login");
            System.out.println("2) register");
            System.out.println("0) quit");
            int option = sc.nextInt();
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

    public void login() throws IOException, ParseException {
        System.out.println("Username");
        String username = sc.next();
        System.out.println("Password");
        String password = sc.next();
        while(!betCompany.checkLogin(username, password)){
            System.out.println("Username or password doesnt exists");
            return;
            //System.out.println("Username");
            //username = sc.next();
            //System.out.println("Password");
            //password = sc.next();
        }
        System.out.println(username+" logged");
        // TODO nacist bets.csv
        // TODO bet + stringy -> ticket
        this.loggedUser = betCompany.getUserByUsername(username);
        if(FileUtils.doesFileExists("data//" + this.loggedUser.getUsername() + "//bets.csv")){
            this.loggedUser.loadTickets();
        }
        //betCompany.getMoneyByCardnumber(this.loggedUser.getCardnumber(), username);
        letsBet();
    }

    public void letsBet() throws IOException {
        while(true) {
            System.out.println("");
            System.out.println("Lets BET");
            System.out.println("----------");
            System.out.println(loggedUser.getUsername());
            System.out.println("$ "+loggedUser.getWallet());
            System.out.println("----------");
            System.out.println("1) new ticket");
            System.out.println("2) active tickets");
            System.out.println("3) bet history");
            System.out.println("4) logout");
            int letsBetOption = sc.nextInt();
            switch (letsBetOption) {
                case 1:
                    newTicket();
                    break;
                case 2:
                    myTickets();
                    break;
                case 3:
                    //myHistory(loggedUser.getUsername());
                    myHistory();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public void newTicket() {
        System.out.println("");
        System.out.println("New Ticket");
        System.out.println("----------");
        System.out.println(loggedUser.getUsername());
        System.out.println("");
        System.out.print("Disponsible money: ");
        System.out.println("$ "+loggedUser.getWallet());
        if(loggedUser.getWallet() == 0){
            System.out.println("Not enough money");
            return;
        }
        System.out.println("----------");
        System.out.println(betCompany.toStringBets());
        System.out.println("Number of match");
        System.out.println("Your bet ( 1 - homeWin, 2 - draw, 3 - awayWin)");
        System.out.println("Choose match: ");
        int matchRow = sc.nextInt();
        Bet myBet = this.betCompany.getBetByIndex(matchRow);
        System.out.println("What are you betting at: ");
        int winnerOption = sc.nextInt();
        System.out.println("How much money you want bet: ");
        int moneyOption = sc.nextInt();
        Ticket myTicket = new Ticket(myBet, winnerOption, moneyOption);
        this.loggedUser.addTicket(myTicket);
        this.loggedUser.removeMoney(moneyOption);
        betCompany.updateUsers();
        FileUtils.appendToFile("data//" + this.loggedUser.getUsername() + "//bets.csv", myTicket.toString());
        //this.loggedUser.setWallet(this.loggedUser.getWallet()-moneyOption);
        //System.out.println(loggedUser.toString());
    }

    public void myTickets(){
        StringBuilder sb = new StringBuilder();
        if(this.loggedUser.getTickets().isEmpty()){
            System.out.println("No active tickets");
        }else{
            for(Ticket ticket : this.loggedUser.getTickets()){
                if(ticket.getStatus().equals("Waiting")) {
                    sb.append(ticket.toString()).append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void myHistory() {
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser.getTickets().isEmpty()) {
            System.out.println("No bet history");
        } else {
            for (Ticket ticket : this.loggedUser.getTickets()) {
                if (ticket.getStatus().equals("Done")) {
                    sb.append(ticket.toString()).append("\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    /*

    public void myHistory(String username){
        System.out.println("Bet history "+username);
        System.out.println("");
        if(FileUtils.checkBetHistory(username)){
            System.out.println("'VYPIS ZAPASU'");
        }
        else{
            System.out.println("No bet history");
        }
    }
*/
    public void register() throws IOException {
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
        int PID = sc.nextInt();
        System.out.println("Card number");
        String cardnumber = sc.next();
        if(betCompany.checkCardnumber(cardnumber)){
            while(betCompany.checkCardnumber(cardnumber)){
                System.out.println("Cardnumber taken, choose another");
                cardnumber = sc.next();
            }
        }
        System.out.println("CVC code");
        int cvc = sc.nextInt();
        int wallet = betCompany.getMoneyByCardnumber(cardnumber, username);
        //ArrayList<User> account = new ArrayList<>();
        User reguser = new User(username,password,PID,cardnumber,cvc, wallet);
        //System.out.println(this.betCompany.toString());
        FileUtils.createFolder(username);
        this.betCompany.addUser(reguser);
        betCompany.bankMoney();
        for(Money money : betCompany.getMoney()){
            //System.out.println(money);
        }
    }

    public void logout() throws IOException {
        System.out.println("");
        System.out.println(loggedUser.getUsername()+ " you are gonna be logged out");
        System.out.println("");
        this.loggedUser = null;
    }

    public void quit() throws IOException, ParseException {
        while(true){
            System.out.println("Are you sure to quit?");
            System.out.println("Press Y for quit");
            System.out.println("Press N to return to the main page");
            String option = sc.next();
            switch(option){
                case "Y":
                    return;
                case "N":
                    intro();
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}
