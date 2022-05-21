package ui;


import app.User;
import utils.FileUtils;
import utils.UsersUtils;
import app.BetCompany;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {

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
        betCompany.loadUsers();
        //System.out.println(betCompany.toString());
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

    public void login(){
        System.out.println("Username");
        System.out.println("Password");
        //TODO
    }

    public void register() throws IOException {
        System.out.println("Username");
        String username = sc.next();
        //TODO
        UsersUtils.checkUsername(username);
        System.out.println("Personal_ID");
        int PID = sc.nextInt();
        System.out.println("Password");
        String password = sc.next();
        System.out.println("Card number");
        int cardnumber = sc.nextInt();
        System.out.println("CVC code");
        int cvc = sc.nextInt();
        //ArrayList<User> account = new ArrayList<>();
        User reguser = new User(username,PID,password,cardnumber,cvc);
        //System.out.println(this.betCompany.toString());
        FileUtils.createData();
        FileUtils.createFolder(username);
        FileUtils.createFile("login.csv");
        this.betCompany.addUser(reguser);
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
