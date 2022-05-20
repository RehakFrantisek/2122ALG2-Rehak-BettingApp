package app;

public class User {
    protected String username;
    protected int PID;
    protected String password;
    protected int cardnumber;
    protected int cvc;

    public User(String username, int PID, String password, int cardnumber, int cvc) {
        this.username = username;
        this.PID = PID;
        this.password = password;
        this.cardnumber = cardnumber;
        this.cvc = cvc;
    }

    public String getUsername() {
        return username;
    }

    public int getPID() {
        return PID;
    }
}
