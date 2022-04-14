
public class Armor extends Item implements Equipable {

    private int reduction;
    private boolean isArmed;

    public Armor(String name, int price, int minLevel, int reduction){
        super(name, price, minLevel);
        this.reduction = reduction;
        this.isArmed = false;
    }

    public void equipItem(){
        isArmed = true;

    }

    public void unequipItem(){
        isArmed = false;
    }

    public boolean isArmed() {
        return isArmed;
    }

    public void setArmed(boolean armed) {
        isArmed = armed;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }



}
