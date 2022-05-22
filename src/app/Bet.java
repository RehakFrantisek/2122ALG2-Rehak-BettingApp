package app;

public class Bet {
    private String homeTeam;
    private String awayTeam;
    private float homeWin;
    private float draw;
    private float awayWin;

    public Bet(String homeTeam, String awayTeam, float homeWin, float draw, float awayWin) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeWin = homeWin;
        this.draw = draw;
        this.awayWin = awayWin;
    }
}
