package app;

import utils.FileUtils;

import java.util.ArrayList;
import java.io.*;

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
        FileUtils.appendToFile("data//"+user.getUsername()+"//login.csv",user.toString());
    }

    public void loadUsers(){

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
