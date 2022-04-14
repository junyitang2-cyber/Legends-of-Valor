/**
 * Weapon class. A weapon is an Item and an Equipable and Attackable
 */
public class Weapon extends Item implements Equipable, Attackable {
    private int damage;
    private boolean isArmed;

    public Weapon(String name, int price, int minLevel, int damage){
        super(name, price, minLevel);
        this.damage = damage;
        this.isArmed = false;
    }

    public void setArmed(boolean armed) {
        isArmed = armed;
    }

    public boolean isArmed() {
        return isArmed;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int calcAttack(int strength, Monster m){
        return (int)((strength+damage)*0.05);
    }

    public void equipItem() {
        isArmed = true;
    }

    public void unequipItem(){
        isArmed = false;
    }




}
