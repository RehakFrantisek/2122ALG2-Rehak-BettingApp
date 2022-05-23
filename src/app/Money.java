package app;

public class Money {
    protected String cardnumber;
    protected int money;

    protected String owner;

    public Money(String cardnumber, int money, String owner) {
        this.cardnumber = cardnumber;
        this.money = money;
        this.owner = owner;
    }

    public int getMoney() {
        return money;
    }

    public String getcardnumber() {
        return cardnumber;
    }

    public String getStatus(){
        return owner;
    }

    public void setOwner(String status) {
        this.owner = owner;
    }
}
