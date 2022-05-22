package app;

public class User {
    protected String username;
    protected int PID;
    protected String password;
    protected int cardnumber;
    protected int cvc;
    protected int wallet;

    public User(String username, int PID, String password, int cardnumber, int cvc, int wallet) {
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

    public int getPID() {
        return PID;
    }

    @Override
    public String toString() {
        return username+";"+PID+";"+password+";"+cardnumber+";"+cvc;
    }
}
