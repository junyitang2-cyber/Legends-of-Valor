//Cave cell in map
public class CaveCell extends Cell {
    private final String type = "CaveCell";

    public CaveCell(int row, int col){
        super("C", row, col);
    }

    public void land(Hero hero){
        hasHero=true;
        System.out.println("Hero landed on Cave cell -- gains 10% of agility for this round.");
        hero.setAgility((int) (hero.getAgility() * (1 + 0.1)));
    }

    public void leave(Hero hero){
        System.out.println("Hero left the Cave cell -- lost the extra 10% of agility for this round.");
        hasHero=false;
        hero.setAgility((int) (hero.getAgility() * (10f/11f)));

    }
}
