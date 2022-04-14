//to determine basic potion usage
public class Potion extends Item implements Consumable {

    private int increase;
    private String affectedAttr;

    public Potion(String name, int price, int minLevel, int increase, String affectedAttr){
        super(name, price, minLevel);
        this.increase = increase;
        this.affectedAttr = affectedAttr;
    }

    public int[] calcUse(){
        int[] res = {0,0,0,0,0};
        if(affectedAttr.contains("Health")){
            res[0]++;
        }
        if(affectedAttr.contains("Mana")){
            res[1]++;
        }
        if(affectedAttr.contains("Strength")){
            res[2]++;
        }
        if(affectedAttr.contains("Dexterity")){
            res[3]++;
        }
        if(affectedAttr.contains("Agility")){
            res[4]++;
        }
        return res;
    }

    public String getAffectedAttr() {
        return affectedAttr;
    }

    public void setAffectedAttr(String affectedAttr) {
        this.affectedAttr = affectedAttr;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }


}
