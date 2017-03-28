package arnoux.com.outerspacemanager.outerspacemanager.Entity;

/**
 * Created by White on 28/03/2017.
 */

public class ShipIdAndAmount {

    private int id;
    private int amount;

    public ShipIdAndAmount(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
