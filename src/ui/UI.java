package ui;


import utils.File;

import java.util.Scanner;

public class UI {
    private Scanner sc = new Scanner(System.in);

    public void mainPage(){
        System.out.println();
        System.out.println(" BETTER ");
        System.out.println(" - APP - ");
    }

    public void intro(){
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
    }

    public void register(){
        System.out.println("Username");
        String username = sc.next();
        System.out.println("Personal_ID");
        int PID = sc.nextInt();
        System.out.println("Password");
        String password = sc.next();
        System.out.println("Card number");
        int cardnumber = sc.nextInt();
        System.out.println("CVC code");
        int cvc = sc.nextInt();
        File.createFolder(username);
    }

    public void quit(){
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
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}
