package app;

public interface IBetCompany {

    public String getName();

    void loadUsers();

    void loadMoney();

    void loadBets();

    boolean checkLogin(String username, String password);

    User getUserByUsername(String username);

    void updateUsers();

    String toStringBets();

    Bet getBetByIndex(int matchRow);

    boolean checkUsername(String username);

    boolean checkCardnumber(String cardnumber);

    float getMoneyByCardnumber(String cardnumber, String username);

    void addUser(User reguser);

    void bankMoney();
}
