package ui;

import app.BetCompany;
import app.IBetCompany;

import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Frantisek Rehak
 */
public class Main {

    public static final String betCompanyName = "Better";
    public static void main(String[] args) throws IOException, ParseException {
        IBetCompany betCompany = new BetCompany(betCompanyName);
        UI ui = new UI(betCompany);
        ui.mainPage();
        ui.intro();
    }
}
