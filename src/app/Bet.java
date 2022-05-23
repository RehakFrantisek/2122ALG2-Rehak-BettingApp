package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Bet {
    private String homeTeam;
    private String awayTeam;
    private float homeWin;
    private float draw;
    private float awayWin;
    private LocalDate date;
    private LocalTime time;

    public Bet(String homeTeam, String awayTeam, float homeWin, float draw, float awayWin, LocalDate date, LocalTime time) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeWin = homeWin;
        this.draw = draw;
        this.awayWin = awayWin;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString()  {
        return homeTeam+";"+awayTeam+";"+homeWin+";"+draw+";"+awayWin+";"+date+";"+time;
    }
}
