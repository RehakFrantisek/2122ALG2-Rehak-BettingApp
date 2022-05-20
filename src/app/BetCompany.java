package app;

import java.util.ArrayList;

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
    }
}
