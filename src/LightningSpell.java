//lightning spell
public class LightningSpell extends Spell {

    private final String type = "Lightning";

    public LightningSpell(String name, int price, int minLevel, int mana, int damage){
        super(name, price, minLevel, mana, damage);
    }

    public int calcAttack(int dexterity, Monster m){
        m.setDodge((int)(m.getDodge()*0.9));
        return (int) (getDamage()+(dexterity/10000)*getDamage());

    }
}
