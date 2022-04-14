// Bash cell in map
public class BushCell extends Cell {
    private final String type = "BushCell";

    public BushCell(int row, int col){
        super("B", row, col);
    }

    public String getType() {
        return type;
    }

    public void land(Hero hero){
        hasHero=true;
        System.out.println("Hero landed on Bush cell -- gains 10% of dexterity for this round.");
        hero.setDexterity((int) (hero.getDexterity() * (1 + 0.1)));
    }

    public void leave(Hero hero){
        System.out.println("Hero left the Bush cell -- lost extra 10% of dexterity for this round");
        hasHero=false;
        hero.setDexterity((int) (hero.getDexterity() * (10f/11f)));
    }
}
