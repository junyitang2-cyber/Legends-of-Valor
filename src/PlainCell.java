//normal plain cell
public class PlainCell extends Cell {
    private final String type = "PlainCell";

    public PlainCell(int row, int col){
        super("P", row, col);
    }


    public void land(Hero hero){
        hasHero=true;
    }

    public void leave(Hero hero){
        hasHero=false;
    }

}
