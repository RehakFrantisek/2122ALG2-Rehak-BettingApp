package app;

public class User {
    protected String username;
    protected int PID;
    protected String password;
    protected String cardnumber;
    protected int cvc;
    protected int wallet;

    public User(String username, String password, int PID, String cardnumber, int cvc, int wallet) {
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

    public int getWallet() {return wallet; }

    public String getCardnumber() { return cardnumber; }

    public int getPID() {
        return PID;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return username+";"+PID+";"+password+";"+cardnumber+";"+cvc+";"+wallet;
    }
}
