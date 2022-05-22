package app;

import java.sql.Time;
import java.sql.Date;

public class Bet {
    private String homeTeam;
    private String awayTeam;
    private float homeWin;
    private float draw;
    private float awayWin;
    private Date date;
    private Time time;

    public Bet(String homeTeam, String awayTeam, float homeWin, float draw, float awayWin, Date date, Time time) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeWin = homeWin;
        this.draw = draw;
        this.awayWin = awayWin;
        this.date = date;
        this.time = time;
    }
}
