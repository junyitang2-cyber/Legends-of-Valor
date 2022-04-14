//Paladin class
public class Paladin extends Hero {
    private final String type = "Paladin";

    public Paladin(String name, int level, int HP, int mana, int strength, int agility, int dexterity, int money, int experience, Lane initLane){
        super(name, level, HP, mana, strength, agility, dexterity, money, experience, initLane);
        colors = new Colors();
    }

    public String getType() {
        return type;
    }

    public void levelUp(){
        while(getExperience()>=getLevel()*10){
            AudioUtility.playSound(AudioUtility.LEVEL_UP);
            setLevel(getLevel()+1);
            setMana((int)(getMana()*1.1));
            setHP(getLevel()*100);
            setAgility((int)(getAgility()*1.05));
            setDexterity((int)(getDexterity()*1.1));
            setStrength((int)(getStrength()*1.1));
            System.out.println(colors.addColor("red","Congratulations! Your hero "+getName()+" leveled up! It's now level "+getLevel()));
        }
    }

}
