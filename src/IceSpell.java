//icespell
public class IceSpell extends Spell {

    private final String type = "Ice";

    public IceSpell(String name, int price, int minLevel, int mana, int damage){
        super(name, price, minLevel, mana, damage);

    }

    public String getType() {
        return type;
    }

    public int calcAttack(int dexterity, Monster m){
        m.setDamage((int)(m.getDamage()*0.9));
        return (int)(getDamage()+(dexterity/10000)*getDamage());

    }


}
