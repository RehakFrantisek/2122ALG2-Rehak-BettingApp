package app;

/**
 *
 * @author Frantisek Rehak
 */
public class Money {
    /**
     * This is class representing bank account.
     */
    protected String cardnumber;
    protected float money;
    protected boolean status = false;

    public Money(String cardnumber, float money, boolean status) {
        this.cardnumber = cardnumber;
        this.money = money;
        this.status = status;
    }

    public float getMoney() {
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
