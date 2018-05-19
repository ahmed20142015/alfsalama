package Model;

/**
 * Created by ahmed on 10/02/18.
 */

public class Services {
    String Name ;
    int ID;
    int PRICE_BEFORE_DISCOUNT;
    int PRICE_After_DISCOUNT;

    public int getPRICE_BEFORE_DISCOUNT() {
        return PRICE_BEFORE_DISCOUNT;
    }

    public void setPRICE_BEFORE_DISCOUNT(int PRICE_BEFORE_DISCOUNT) {
        this.PRICE_BEFORE_DISCOUNT = PRICE_BEFORE_DISCOUNT;
    }

    public int getPRICE_After_DISCOUNT() {
        return PRICE_After_DISCOUNT;
    }

    public void setPRICE_After_DISCOUNT(int PRICE_After_DISCOUNT) {
        this.PRICE_After_DISCOUNT = PRICE_After_DISCOUNT;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
