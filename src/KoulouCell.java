//koulou cell
public class KoulouCell extends Cell {
        private final String type = "KoulouCell";

    public KoulouCell(int row, int col){
        super("K", row, col);
    }

    public void land(Hero hero){
        hasHero=true;
        System.out.println("Hero landed on Koulou cell -- gains 10% of strength for this round\n");
        hero.setStrength((int) (hero.getStrength() * (1 + 0.1)));
    }

    public void leave(Hero hero){
        hasHero=false;
        System.out.println("Hero left the Koulou cell -- lost the extra 10% of strength for this round\n");
        hero.setStrength((int) (hero.getStrength() * (10f/11f)));
    }
}
