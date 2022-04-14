//class that represents a lane
import java.util.HashSet;
import java.util.Set;

public class Lane {

    private int maxMonsterRow;
    private String name;
    private int maxExplored=7;
    private int leftCol;
    Set<Hero> heroSet=new HashSet<>();


    public void getIn(Hero hero){
        heroSet.add(hero);
        updateMaxExplored();
    }

    public void getOut(Hero hero){
        heroSet.remove(hero);
        updateMaxExplored();
    }
    public int updateMaxExplored(){
        for (Hero hero:heroSet
             ) {
            if(hero.getRow()<maxExplored) maxExplored=hero.getRow();
        }
        return 0;
    }


    public Lane(){

    }
    public Lane(String name){
        this.name = name;
        maxMonsterRow = 0;
        maxExplored = 7;
        leftCol = 0;

    }

    public void increaseMaxMonsterRow(){
        maxMonsterRow++;
    }

    public void increaseMaxExplored(){
        maxExplored++;
    }


    public int getMaxMonsterRow() {
        return maxMonsterRow;
    }

    public void setMaxMonsterRow(int maxMonsterRow) {
        this.maxMonsterRow = maxMonsterRow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxExplored() {
        updateMaxExplored();
        return maxExplored;
    }

    public void setMaxExplored(int maxExplored) {
        this.maxExplored = maxExplored;
    }

    public int getLeftCol() {
        return leftCol;
    }

    public void setLeftCol(int leftCol) {
        this.leftCol = leftCol;
    }




}
