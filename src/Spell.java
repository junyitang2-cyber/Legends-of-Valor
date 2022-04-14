//basic info of spell
public abstract class Spell extends Item implements Attackable {
    private int mana;
    private int damage;

    public Spell(String name, int price, int minLevel, int mana, int damage){
        super(name, price, minLevel);
        this.mana = mana;
        this.damage = damage;

    }
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
