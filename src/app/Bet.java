package app;

import java.util.Date;

public class Bet {
    private String homeTeam;
    private String awayTeam;
    private float homeWin;
    private float draw;
    private float awayWin;
    private Date date;
    private Date time;

    public Bet(String homeTeam, String awayTeam, float homeWin, float draw, float awayWin, Date date, Date time) {
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
