package app;

import utils.FileUtils;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

import static utils.FileUtils.readCSV;

public class BetCompany {
    public String name;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Bet> availableBets = new ArrayList<>();

    public BetCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        this.users.add(user);
        FileUtils.appendToFile("data//login.csv",user.toString());
    }

    public void loadUsers(){
        File file = new File("data//login.csv");
        if(file.exists() && file.length() != 0) {
            String[] lines = readCSV("data//login.csv");
            //System.out.println(lines.length);
            try {
                for (String line : lines) {
                    String[] parm = line.split(";");
                    User user = new User(parm[0], Integer.parseInt(parm[1]), parm[2], Integer.parseInt(parm[3]), Integer.parseInt(parm[4]),Integer.parseInt(parm[5]));
                    this.users.add(user);
                }
            }finally {
                System.out.println("Users successfully loaded");
            }
        }
        else{
            System.out.println("login.csv is empty");
            return;
        }
    }

    public boolean checkUsername(String username){
        for(User user : this.users){
            if(user.getUsername().equals(username)){
                //System.out.println("User exists");
                return false;
            }
        }
        return true;
    }

    public boolean checkLogin(String username, String password){
        for(User user : this.users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        //System.out.println("Wrong login");
        return false;
    }

    public User getUserByUsername(String username){
        for(User user : this.users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void loadBets(){
        String[] bets = readCSV("betData.csv");
        //System.out.println(lines.length);
        for(String bet : bets){
            String[] parm = bet.split(";");
            Bet bet2 = new Bet(parm[0],parm[1],Float.parseFloat(parm[2]),Float.parseFloat(parm[3]),Float.parseFloat(parm[4]), (Date) Date.from(Instant.parse(parm[5])), (Time) Time.from(Instant.parse(parm[6])));
            this.availableBets.add(bet2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user: this.users) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}
