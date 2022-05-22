package ui;


import app.User;
import utils.FileUtils;
import app.BetCompany;
import utils.OtherUtils;


import java.io.IOException;
import java.util.Scanner;


public class UI {

    private User loggedUser = null;
    private BetCompany betCompany;
    private Scanner sc = new Scanner(System.in);

    public UI(BetCompany betCompany){
        this.betCompany = betCompany;
    }

    public void mainPage(){
        System.out.println();
        System.out.println(this.betCompany.getName());
        System.out.println("- APP -");
    }

    public void intro() throws IOException {
        FileUtils.createData();
        FileUtils.createFile("login.csv","data");
        betCompany.loadUsers();
        //betCompany.loadBets();
        // TODO loadBets
        //System.out.println(betCompany.toString());
        //System.out.println(betCompany.toString2());
        while(true){
            System.out.println();
            System.out.println("1) login");
            System.out.println("2) register");
            System.out.println("0) quit");
            int option = sc.nextInt();
            switch(option){
                case 1:
                    login();
                    //loadUserData();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    quit();
                    System.out.println("Have a good day!");
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public void login() throws IOException {
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
        this.loggedUser = betCompany.getUserByUsername(username);
        letsBet();
        //TODO
    }

    public void letsBet() throws IOException {
        while(true) {
            System.out.println("");
            System.out.println("Lets BET");
            System.out.println("----------");
            System.out.println(loggedUser.getUsername());
            System.out.println("'MONEY'");
            System.out.println("----------");
            System.out.println("1) new ticket");
            System.out.println("2) active tickets");
            System.out.println("3) bet history");
            System.out.println("4) logout");
            int letsBetOption = sc.nextInt();
            switch (letsBetOption) {
                case 1:
                    //newTicket();
                case 2:
                    //myTickets();
                case 3:
                    myHistory(loggedUser.getUsername());
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

    public void register() throws IOException {
        System.out.println("Username");
        String username = sc.next();
        if(!betCompany.checkUsername(username)){
            while(!betCompany.checkUsername(username)){
                System.out.println("Username taken, choose another");
                username = sc.next();
            }
        }
        //TODO check
        System.out.println("Personal_ID");
        int PID = sc.nextInt();
        System.out.println("Password");
        String password = sc.next();
        System.out.println("Card number");
        int cardnumber = sc.nextInt();
        System.out.println("CVC code");
        int cvc = sc.nextInt();
        int wallet = OtherUtils.makeMoney();
        //ArrayList<User> account = new ArrayList<>();
        User reguser = new User(username,PID,password,cardnumber,cvc, wallet);
        //System.out.println(this.betCompany.toString());
        FileUtils.createFolder(username);
        this.betCompany.addUser(reguser);
    }

    public void logout() throws IOException {
        System.out.println("");
        System.out.println(loggedUser.getUsername()+ " you are gonna be logged out");
        System.out.println("");
        this.loggedUser = null;
    }

    public void quit() throws IOException {
        while(true){
            System.out.println("Are you sure to quit?");
            System.out.println("Press Y for quit");
            System.out.println("Press N to return to the main page");
            String option = sc.next();
            switch(option){
                case "Y":
                    return;
                    //TODO repair
                case "N":
                    intro();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}
