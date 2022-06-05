package app;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IBetCompany {

    public String getName();

    void loadUsers() throws FileNotFoundException;

    void loadMoney() throws FileNotFoundException;

    void loadBets() throws FileNotFoundException;

    boolean checkLogin(String username, String password);

    User getUserByUsername(String username);

    void updateUsers() throws IOException;

    String toStringBets();

    Bet getBetByIndex(int matchRow);

    boolean checkUsername(String username);

    boolean checkCardnumber(String cardnumber);

    float getMoneyByCardnumber(String cardnumber, String username);

    void addUser(User reguser);

    void bankMoney() throws IOException;

    void getBinaryData() throws IOException;
}
