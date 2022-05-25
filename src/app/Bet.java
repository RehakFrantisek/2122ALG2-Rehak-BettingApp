package app;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Frantisek Rehak
 */
public class Bet {
    /**
     * This is class representing bet
     */
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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public float getHomeWin() {
        return homeWin;
    }

    public float getDraw() {
        return draw;
    }

    public float getAwayWin() {
        return awayWin;
    }

    @Override
    public String toString()  {
        return homeTeam+";"+awayTeam+";"+homeWin+";"+draw+";"+awayWin+";"+date+";"+time;
    }
}
