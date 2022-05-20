package ui;


import app.BetCompany;

import java.io.IOException;

public class Main {

    public static final String betCompanyName = "Better";
    public static void main(String[] args) throws IOException {
        BetCompany betCompany = new BetCompany(betCompanyName);
        UI ui = new UI(betCompany);
        ui.mainPage();
        ui.intro();
    }
}
