package ui;


import app.BetCompany;

public class Main {

    public static final String betCompanyName = "Better";
    public static void main(String[] args) {
        BetCompany betCompany = new BetCompany(betCompanyName);
        UI ui = new UI(betCompany);
        ui.mainPage();
        ui.intro();
    }
}
