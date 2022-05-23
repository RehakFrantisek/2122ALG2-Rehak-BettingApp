package app;

public class Money {
    protected String cardnumber;
    protected int money;

    protected boolean status = false;

    public Money(String cardnumber, int money, boolean status) {
        this.cardnumber = cardnumber;
        this.money = money;
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public String getcardnumber() {
        return cardnumber;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return cardnumber+";"+money+";"+status;
    }
}
