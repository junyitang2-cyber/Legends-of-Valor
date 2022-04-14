//FireSpell
public class FireSpell extends Spell {

    private final String type = "Fire";
    public FireSpell(String name, int price, int minLevel, int mana, int damage){
        super(name, price, minLevel, mana, damage);
    }

    public String getType() {
        return type;
    }

    public int calcAttack(int dexterity, Monster m){
        m.setDefense((int)(m.getDefense()*0.9));
        return (int)(getDamage()+(dexterity/10000)*getDamage());

    }





}
