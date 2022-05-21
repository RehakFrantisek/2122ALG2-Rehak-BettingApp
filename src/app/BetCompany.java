package app;

import utils.FileUtils;

import java.util.ArrayList;
import java.io.*;

import static utils.FileUtils.readCSV;

public class BetCompany {
    public String name;
    private ArrayList<User> users = new ArrayList<>();

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
        String[] lines = readCSV("data//login.csv");
        //System.out.println(lines.length);
        for(String line : lines){
            String[] parm = line.split(";");
            User user = new User(parm[0],Integer.parseInt(parm[1]),parm[2],Integer.parseInt(parm[3]),Integer.parseInt(parm[4]));
            this.users.add(user);
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
