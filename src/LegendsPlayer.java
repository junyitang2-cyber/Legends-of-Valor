//game player
import java.util.ArrayList;


public class LegendsPlayer extends Player {
    private ArrayList<Hero> heroes;
    private int pRow;
    private int pCol;

    public LegendsPlayer(String name, String icon) {
        super(name, "P");
        heroes = new ArrayList<Hero>();
        pRow = 4;
        pCol = 4;
    }


    public boolean isValidMove(LovMap grid, int row, int col){
        boolean isValid = false;
        return false;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero(Hero newH) {
        heroes.add(newH);
    }

    public int getPRow() {
        return pRow;
    }

    public void setPRow(int pRow) {
        this.pRow = pRow;
    }

    public int getPCol() {
        return pCol;
    }

    public void setPCol(int pCol) {
        this.pCol = pCol;
    }

    public void printPlayerHeroes() {
        Printer p = new Printer();
        p.printHeroes(heroes);
    }


}
